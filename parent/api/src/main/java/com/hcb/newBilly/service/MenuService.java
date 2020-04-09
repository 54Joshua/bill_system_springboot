package com.hcb.newBilly.service;

import com.hcb.newBilly.dto.MenuDTO;
import com.hcb.newBilly.po.MenuPO;
import com.hcb.newBilly.vo.MenuRelationVO;

import java.util.List;

public interface MenuService {
    void saveMenu(MenuDTO menuDTO);
    void updateMenu(MenuDTO menuDTO);
    List<MenuRelationVO> findMenu();
    void deleteMenu(String menuId);
    List<MenuPO> findMenuPOS();
}
