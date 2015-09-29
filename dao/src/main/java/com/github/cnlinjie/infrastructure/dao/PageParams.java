package com.github.cnlinjie.infrastructure.dao;

/**
 * @author Linjie
 * @date 2015/9/29.
 */
public class PageParams {

    private int pageIndex = 15;
    private int pageSize;
    private long startRow;

    public static PageParams page(int pageIndex,int pageSize) {
        return new PageParams(pageIndex,pageSize);
    }

    public static PageParams page(long startRow,int pageSize) {
        return new PageParams(startRow,pageSize);
    }

    public static PageParams page(long startRow) {
        return new PageParams(startRow);
    }

    public static PageParams page(int pageIndex) {
        return new PageParams(pageIndex);
    }

    public PageParams(long startRow, int pageSize) {
        this.startRow = startRow;
        this.pageSize = pageSize;
    }

    public PageParams(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public PageParams(long startRow) {
        this.startRow = startRow;
    }

    public PageParams(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
