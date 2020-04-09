package com.hcb.newBilly.serviceImp;

import com.hcb.newBilly.common.BillyException;
import com.hcb.newBilly.common.EntityUtils;
import com.hcb.newBilly.common.IdCreator;
import com.hcb.newBilly.dao.MenuDao;
import com.hcb.newBilly.dto.MenuDTO;
import com.hcb.newBilly.po.MenuPO;
import com.hcb.newBilly.service.MenuService;
import com.hcb.newBilly.vo.MenuRelationVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MenuServiceImp implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public void saveMenu(MenuDTO menuDTO) {

        String parentMenuId = menuDTO.getParentId();
        String parentId = StringUtils.isBlank(parentMenuId) ? "-1" : parentMenuId;
        List<MenuPO> parentMenuPOS = menuDao.findMenuByParentId(parentId);
        if (!CollectionUtils.isEmpty(parentMenuPOS)) {
            Set<String> menuNames = parentMenuPOS.stream().map(MenuPO::getMenuName).collect(Collectors.toSet());
            if (menuNames.contains(menuDTO.getMenuName())) {
                throw new BillyException("目录【" + menuDTO.getMenuName() + "】重复");
            }
        }
        MenuPO menuPO = new MenuPO();
        EntityUtils.copyProperty(menuDTO, menuPO);
        menuPO.setMenuId(IdCreator.getNextId());
        menuPO.setParentId(parentId);
        menuDao.saveMenu(menuPO);
    }

    @Override
    public void updateMenu(MenuDTO menuDTO) {
        String parentId = StringUtils.isBlank(menuDTO.getParentId()) ? "-1" : menuDTO.getParentId();
        int count = menuDao.checkUpdate(parentId, menuDTO.getMenuId(), menuDTO.getMenuName());
        if (count > 0) {
            throw new BillyException("目录【" + menuDTO.getMenuName() + "】重复");
        }
        MenuPO menuPO = new MenuPO();
        EntityUtils.copyProperty(menuDTO, menuPO);
        menuDao.updateMenu(menuPO);
    }

    @Override
    public List<MenuRelationVO> findMenu() {
        List<MenuRelationVO> relationVOS = new ArrayList<>();
        List<MenuPO> parentMenuPos = menuDao.findMenuByParentId("-1");
        if (CollectionUtils.isEmpty(parentMenuPos)) {
            return relationVOS;
        }
        parentMenuPos.forEach(menuPO -> {
            MenuRelationVO childMenu = new MenuRelationVO();
            EntityUtils.copyProperty(menuPO, childMenu);
            childMenu.setParentName("");
            relationVOS.add(childMenu);
        });
        List<MenuRelationVO> newRelationVOS = relationVOS.stream().sorted(Comparator.comparing(MenuRelationVO::getOrder)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(newRelationVOS)) {
            for (MenuRelationVO menuRelation : newRelationVOS) {
                getMenuRelationVOS(menuRelation);
            }
        }
        return newRelationVOS;
    }

    private void getMenuRelationVOS(MenuRelationVO parentRelationVO) {
        String menuId = parentRelationVO.getMenuId();
        List<MenuPO> childMenuPOS = menuDao.findMenuByParentId(menuId);
        List<MenuRelationVO> childRelationVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(childMenuPOS)) {
            List<MenuRelationVO> relationVOS = new ArrayList<>();
            for (MenuPO childMenu : childMenuPOS) {
                MenuRelationVO relationVO = new MenuRelationVO();
                EntityUtils.copyProperty(childMenu, relationVO);
                relationVO.setParentName(parentRelationVO.getMenuName());
                getMenuRelationVOS(relationVO);
                relationVOS.add(relationVO);
            }
            childRelationVOS = relationVOS.stream().sorted(Comparator.comparing(MenuRelationVO::getOrder)).collect(Collectors.toList());
        }
        parentRelationVO.setChildMenu(childRelationVOS);
    }

    @Override
    public void deleteMenu(String menuId) {
        List<MenuPO> menuPOS = menuDao.findMenuByParentId(menuId);
        if (!CollectionUtils.isEmpty(menuPOS)) {
            throw new BillyException("该菜单下还有子菜单，不能删除");
        }
        menuDao.deleteMenu(menuId);
    }

    @Override
    public List<MenuPO> findMenuPOS() {
        List<MenuPO> menuPOList = menuDao.findMenu();
        return menuPOList;
    }
}
