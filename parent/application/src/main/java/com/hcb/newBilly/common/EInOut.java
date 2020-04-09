package com.hcb.newBilly.common;

public enum EInOut {
    IN("41f6a0b1c028447fa0e2e846aad16ce3","进"),
    OUT("d38878d66ee64df2a84c5791917930ec","出");
    private String dicId;
    private String dicName;

    EInOut(String dicId, String dicName) {
        this.dicId = dicId;
        this.dicName = dicName;
    }

    public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }
}
