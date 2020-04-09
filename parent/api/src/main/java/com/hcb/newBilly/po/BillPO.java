package com.hcb.newBilly.po;

import com.hcb.newBilly.common.DicField;
import com.hcb.newBilly.vo.DictionaryVO;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class BillPO {
    private String billId;
    private String inOutStatus;
    @DicField
    private DictionaryVO inOutStatusD;
    private String useFor;
    @DicField
    private DictionaryVO useForD;
    private Double sum;
    private String remark;
    private String operator;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer dataStatus;
    private Date billTime;
}
