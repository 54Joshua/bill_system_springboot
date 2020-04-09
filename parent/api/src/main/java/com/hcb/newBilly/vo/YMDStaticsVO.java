package com.hcb.newBilly.vo;

import lombok.Data;

import java.util.List;

@Data
public class YMDStaticsVO {
    private List<TimeSumStaticsVO> timeSumStaticsVOS;
    private String  title;
    private String timeTypeStr;
}
