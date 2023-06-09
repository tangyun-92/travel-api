package com.tang.travel.model.req.scenery;

import io.swagger.annotations.ApiModelProperty;

public class SceneryCategoryQueryReq {

    @ApiModelProperty(value = "分类名称", name = "name", dataType = "String")
    private String name;

    @ApiModelProperty(hidden = true)
    private String isDel;

    @ApiModelProperty(value = "当前第几页", name = "current", dataType = "Integer")
    private Integer current = 1;

    @ApiModelProperty(value = "每页显示条数", name = "pageSize", dataType = "Integer")
    private Integer pageSize = 10;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

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
        return "SceneryCategoryQueryReq{" +
                "name='" + name + '\'' +
                ", isDel='" + isDel + '\'' +
                ", current=" + current +
                ", pageSize=" + pageSize +
                '}';
    }
}