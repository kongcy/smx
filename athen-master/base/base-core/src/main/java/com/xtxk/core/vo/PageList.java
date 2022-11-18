package com.xtxk.core.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Description 分页数组
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2021/7/15
 */
@Data
public class PageList<T> implements Serializable {
    private long total;
    private int pageNum;
    private T data;

    public PageList() {
    }

    public PageList(long total, int pageNum, T data) {
        this.total = total;
        this.pageNum = pageNum;
        this.data = data;
    }

    /**
     * 转成分页参数
     *
     * @param total   总页数
     * @param pageNum 当前页数
     * @param data    集合
     */
    public static <T> PageList<T> toPage(long total, int pageNum, T data) {
        return new PageList(total, pageNum, data);
    }

    public static PageList defaultPageList() {
        return new PageList(0, 0, new ArrayList<>());
    }
}
