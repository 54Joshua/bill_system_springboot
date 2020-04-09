package com.hcb.newBilly.po;

import lombok.Data;

@Data
public class DictionaryPO {
    private String dicId;
    private String dictionaryEn;
    private String dictionaryContent;
    private Integer dataStatus;
    private Boolean changeAble;
    private String owner;
}
