package com.hcb.newBilly.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;


@Data
public class BillDTO {
    private String billId;
    private String inOutStatus;
    private String dellWay;
    private String cardId;
    /**
     * 用途
     */
    private String useFor;
    private Double sum;
    private String remark;
    private String operator;
    private Timestamp updateTime;
    private Date billTime;
}
