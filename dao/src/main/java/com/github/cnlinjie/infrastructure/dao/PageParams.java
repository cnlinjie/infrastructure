package com.github.cnlinjie.infrastructure.dao;

import com.github.cnlinjie.infrastructure.util.spring.Assert;

/**
 * @author Linjie
 * @date 2015/9/29.
 */
public class PageParams {

    private int pageIndex = 1;
    private int pageSize = 15;
    private long startRow = 0;

    /**
     * 分页参数
     * @param pageIndex   第{pageIndex}页
     * @param pageSize 每页 {pageSize} 条
     * @return
     */
    public static PageParams page(int pageIndex, int pageSize) {
        return new PageParams(pageIndex, pageSize);
    }

    /**
     * 分页参数
     * @param startRow 从第 {startRow} 条开始
     * @param pageSize 往后取 {pageSize}  条
     * @return
     */
    public static PageParams page(long startRow, int pageSize) {
        return new PageParams(startRow, pageSize);
    }

    /**
     * 分页参数 ，默认往后取 15 条
     * @param startRow 从第 {startRow} 条开始
     * @return
     */
    public static PageParams page(long startRow) {
        return new PageParams(startRow);
    }

    /**
     * 分页参数，默认每页 15 条
     * @param pageIndex  第{pageIndex}页
     * @return
     */
    public static PageParams page(int pageIndex) {
        return new PageParams(pageIndex);
    }

    /**
     * 分页参数
     * @param startRow 从第 {startRow} 条开始
     * @param pageSize 往后取 {pageSize}  条
     * @return
     */
    public PageParams(long startRow, int pageSize) {
        setStartRow(startRow);
        setPageSize(pageSize);
    }
    /**
     * 分页参数
     * @param pageIndex   第{pageIndex}页
     * @param pageSize 每页 {pageSize} 条
     * @return
     */
    public PageParams(int pageIndex, int pageSize) {
        setPageSize(pageSize);
        setPageIndex(pageIndex);
    }

    /**
     * 分页参数 ,默认往后取 15 条
     * @param startRow 从第 {startRow} 条开始
     * @return
     */
    public PageParams(long startRow) {
        setStartRow(startRow);
    }

    /**
     * 分页参数，默认每页 15 条
     * @param pageIndex  第{pageIndex}页
     * @return
     */
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
