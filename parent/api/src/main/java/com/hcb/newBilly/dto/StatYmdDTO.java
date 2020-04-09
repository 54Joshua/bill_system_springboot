package com.hcb.newBilly.dto;

import lombok.Data;

import java.sql.Date;


@Data
public class StatYmdDTO {
    private String inOutStatus;
    private String useFor;
    /**
     * 1、年；2、月；3、天
     */
    private Integer timeType;
    private Date time;
    private String operator;

}
