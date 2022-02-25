package com.dl.book.domain;


/**
 * PageQuery.
 * @author jiangmh
 */
public class PageQuery {

    private Integer pageNum;

    private Integer pageSize;


    public Integer getPageNum() {
        return pageNum = (pageNum == null || pageNum == 0) ? 1 : this.pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize = (pageSize == null || pageSize == 0) ? 10 : this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
