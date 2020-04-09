package com.hcb.newBilly.dto;

import lombok.Data;

@Data
public class MenuDTO {
    private String menuId;
    private String menuName;
    private String parentId;
    private String addr;
    private String tag;
    private Integer order;
}
