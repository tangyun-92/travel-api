package com.tang.travel.model.req;

import io.swagger.annotations.ApiModelProperty;

public class PageReq {

    @ApiModelProperty(value = "当前第几页", name = "current", dataType = "Integer")
    private Integer current = 1;

    @ApiModelProperty(value = "每页显示条数", name = "pageSize", dataType = "Integer")
    private Integer pageSize = 10;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageReq{" +
                "current=" + current +
                ", pageSize=" + pageSize +
                '}';
    }
}
