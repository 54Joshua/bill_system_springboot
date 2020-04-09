package com.hcb.newBilly.po;

import lombok.Data;

@Data
public class MenuPO {
    private String menuId;
    private String menuName;
    private String parentId;
    private String addr;
    private String tag;
    private Integer order;
    private Integer dataStatus;
}
