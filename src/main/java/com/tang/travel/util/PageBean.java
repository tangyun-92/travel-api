package com.tang.travel.util;

import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public class PageBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int current;
    private int pageSize;
    private long total;
    private int pages;
    private List<T> list;

    public PageBean(List<T> list) {
        this(list, 5);
    }

    public PageBean(List<T> data, int navigatePages) {
        if (data instanceof Page) {
            Page page = (Page) data;
            this.current = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.pages = page.getPages();
            this.list = page;
            this.total = page.getTotal();
        } else if (data instanceof Collection) {
            this.current = 1;
            this.pageSize = data.size();
            this.pages = this.pageSize > 0 ? 1 : 0;
            this.list = data;
            this.total = data.size();
        }

    }
}
