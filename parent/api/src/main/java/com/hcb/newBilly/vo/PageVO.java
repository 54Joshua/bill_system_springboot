package com.hcb.newBilly.vo;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

@Data
public class PageVO<T> {
    private Integer size;
    private Integer pageNum;
    private Long total;
    private List<T> dataList;

    public PageVO(Page<T> page) {
        this.dataList = page.getResult();
        this.pageNum = page.getPageNum();
        this.size = page.getPageSize();
        this.total=page.getTotal();
    }
}
