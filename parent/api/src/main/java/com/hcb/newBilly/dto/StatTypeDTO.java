package com.hcb.newBilly.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StatTypeDTO {

    private String inOutStatus;
    private String useFor;
    private Integer billTimeType;
    private Date atTime;
    private Date startTime;
    private Date endTime;
    private String operator;
}
