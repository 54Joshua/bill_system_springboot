package com.hcb.newBilly.vo;

import com.hcb.newBilly.common.DicField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DicStaticsVO {
    @DicField
    private DictionaryVO dicContentVO;
    private BigDecimal sum;
    private Integer count;
}
