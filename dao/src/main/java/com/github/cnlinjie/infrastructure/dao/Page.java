package com.github.cnlinjie.infrastructure.dao;

import java.util.List;

/**
 * @author Linjie
 * @date 2015/9/21.
 */
public class Page<T> {

    private int pageIndex = 1; //  当前页

    private int pageSize = 10; // 每页记录数

    private int pageNumber; // 页数

    private long totalCount; // 所有记录数

    private List<T> content; // 当前页内容

    /**
     * 获取当前页
     * @return
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * 设置当前页
     * @param pageIndex
     */
    public void setPageIndex(int pageIndex) {
        if (pageIndex <= 0)
            pageIndex = 1;
        this.pageIndex = pageIndex;
    }

    /**
     * 获取每页记录数
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页记录数
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取总页数
     * @return
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * 获取总记录数
     * @return
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * 设置记录数
     * @param totalCount
     */
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        // 根据总的记录数生成分页
        if (totalCount >  0) {
            // 计算总的页数
            if (totalCount % pageSize == 0) {
                this.pageNumber = (int) (totalCount / pageSize);
            } else {
                this.pageNumber = (int) (totalCount / pageSize + 1);
            }
        }
    }

    /**
     * 获取当前页内容
     * @return
     */
    public List<T> getContent() {
        return content;
    }

    /**
     * 设置当前页内容
     * @param content
     */
    public void setContent(List<T> content) {
        this.content = content;
    }


    public Page() {
    }

    public Page(List<T> content, long totalCount, int pageIndex, int pageSize) {

        this.setPageSize(pageSize);
        this.setPageIndex(pageIndex);
        this.setContent(content);
        this.setTotalCount(totalCount);
    }
}
