package com.hcb.newBilly.dao;

import com.hcb.newBilly.po.MenuPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
public interface MenuDao {
    @CacheEvict(value = "billEhcache", allEntries = true)
    void saveMenu(MenuPO menuPO);

    @CacheEvict(value = "billEhcache", allEntries = true)
    void updateMenu(MenuPO menuPO);

    @Cacheable(value = "billEhcache", key = "'findMenuByParentId_'+#parentId")
    List<MenuPO> findMenuByParentId(@Param("parentId") String parentId);

    @Cacheable(value = "billEhcache", key = "'findMenu_all'")
    List<MenuPO> findMenu();

    @CacheEvict(value = "billEhcache", allEntries = true)
    void deleteMenu(@Param("menuId") String menuId);


    int checkUpdate(@Param("parentId") String parentId, @Param("menuId") String menuId, @Param("menuName") String menuName);
}
