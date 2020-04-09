package com.hcb.newBilly.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuRelationVO {
    private String menuId;
    private String menuName;
    private String parentId;
    private String parentName;
    private String addr;
    private String tag;
    private Integer order;
    private List<MenuRelationVO> childMenu;
}
