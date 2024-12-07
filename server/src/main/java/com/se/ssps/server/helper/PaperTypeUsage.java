package com.se.ssps.server.helper;

import com.se.ssps.server.entity.PageSize;

public class PaperTypeUsage {
    private PageSize pageSize; // A3, A4, v.v.
    private Integer totalPages;

    public PaperTypeUsage(PageSize pageSize, Integer totalPages) {
        this.pageSize = pageSize;
        this.totalPages = totalPages;
    }

    // Getter và Setter
    public PageSize getPageSize() {
        return pageSize;
    }

    public void setPageSize(PageSize pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
