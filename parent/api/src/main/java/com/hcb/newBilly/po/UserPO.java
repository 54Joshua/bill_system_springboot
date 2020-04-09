package com.hcb.newBilly.po;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserPO {
    private String userId;
    private String userAccount;
    private String userName;
    private String password;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer dataStatus;
}
