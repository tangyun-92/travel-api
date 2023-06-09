package com.tang.travel.model.req.scenery;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SceneryCategorySaveReq {
    @ApiModelProperty(value = "id", name = "id", dataType = "Integer")
    private Integer id;

    @NotEmpty(message = "【分类名称】不能为空")
    @ApiModelProperty(value = "分类名称", name = "name", dataType = "String", required = true)
    private String name;

    @ApiModelProperty(value = "父级id", name = "parentId", dataType = "Integer")
    private Integer parentId = 0;

    @NotNull(message = "【排序】不能为空")
    @ApiModelProperty(value = "排序", name = "sort", dataType = "Integer", required = true)
    private Integer sort;

    @ApiModelProperty(hidden = true)
    private String isDel = "0";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "SceneryCategorySaveReq{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", sort=" + sort +
                ", isDel='" + isDel + '\'' +
                '}';
    }
}