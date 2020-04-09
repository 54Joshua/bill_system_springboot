package com.hcb.newBilly.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StatDictionaryDTO {

    private String inOutStatus;
    private Integer billTimeType;
    private Date atTime;
    private Date startTime;
    private Date endTime;
    private String operator;
}
