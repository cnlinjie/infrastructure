package com.github.cnlinjie.infrastructure.dao.infrastructure.dao;

import com.github.cnlinjie.infrastructure.util.spring.Assert;
import org.apache.commons.lang3.Validate;

/**
 * @author Linjie
 * @date 2015/9/29.
 */
public class PageParams {

    private int pageIndex = 15;
    private int pageSize = 1;
    private long startRow = 1;

    public static PageParams page(int pageIndex, int pageSize) {
        return new PageParams(pageIndex, pageSize);
    }

    public static PageParams page(long startRow, int pageSize) {
        return new PageParams(startRow, pageSize);
    }

    public static PageParams page(long startRow) {
        return new PageParams(startRow);
    }

    public static PageParams page(int pageIndex) {
        return new PageParams(pageIndex);
    }

    public PageParams(long startRow, int pageSize) {
        setStartRow(startRow);
        setPageSize(pageSize);
    }

    public PageParams(int pageIndex, int pageSize) {
        setPageIndex(pageIndex);
        setPageSize(pageSize);
    }


    public PageParams(long startRow) {
        setStartRow(startRow);
    }

    public PageParams(int pageIndex) {
        setPageIndex(pageIndex);
    }


    private void setPageIndex(int pagesIndex) {
        Assert.isTrue(pagesIndex > 0, "can't pagesIndex less than 0");
        this.pageIndex = pagesIndex;
        setStartRow((pageIndex - 1) * this.pageSize);
    }

    private void setPageSize(int pageSize) {
        Assert.isTrue(pageSize > 0, "can't pageSize less than 0");
        this.pageSize = pageSize;
    }

    private void setStartRow(long startRow) {
        Assert.isTrue(startRow > -1, "can't startRow less than -1");
        this.startRow = startRow;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getStartRow() {
        return startRow;
    }

    public int getStartRowByInt() {
        return (int) startRow;
    }
}
