package com.xtxk.core.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageInfo implements Serializable {
    //当前页
    private int currentPage = 1;
    //每页数量
    private int limit = 10;

    private String orderBy;

    public PageInfo() {
    }

    public PageInfo(int currentPage, int limit) {
        this.currentPage = currentPage;
        this.limit = limit;
    }

    public PageInfo(int currentPage, int limit, String orderBy) {
        this.currentPage = currentPage;
        this.limit = limit;
        this.orderBy = orderBy;
    }

}
