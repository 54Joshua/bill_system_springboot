package com.hcb.newBilly.common;

public enum EStatus {
    USABLE(1,"可用"),
    FORBIDDEN(2,"禁用"),
    DEL(3,"删除");
    private Integer status;
    private String common;

    EStatus(Integer status, String common) {
        this.status = status;
        this.common = common;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }
}
