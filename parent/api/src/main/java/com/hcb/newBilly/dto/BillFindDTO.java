package com.hcb.newBilly.dto;

import com.hcb.newBilly.vo.BasePage;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class BillFindDTO extends BasePage {
    private String inOutStatus;
    private String useFor;
    private Integer billTimeType;
    private Date atTime;
    private Date startTime;
    private Date endTime;
    private String cardId;
    private String operator;
}
