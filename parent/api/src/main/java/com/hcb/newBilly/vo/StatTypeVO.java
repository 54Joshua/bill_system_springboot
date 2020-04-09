package com.hcb.newBilly.vo;

import lombok.Data;

import java.util.List;

@Data
public class StatTypeVO {
    private List<SelectStaticsVO> selectStaticsVOS;
    private Integer totalCount;
    private Double totalSum;
}
