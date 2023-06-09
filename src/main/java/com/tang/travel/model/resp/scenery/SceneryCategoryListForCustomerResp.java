package com.tang.travel.model.resp.scenery;

import java.util.ArrayList;
import java.util.List;

public class SceneryCategoryListForCustomerResp {
    private Integer id;

    private String name;

    private Integer parentId;

    private Integer sort;

    private List<SceneryCategoryListForCustomerResp> children = new ArrayList<>();

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

    public List<SceneryCategoryListForCustomerResp> getChildren() {
        return children;
    }

    public void setChildren(List<SceneryCategoryListForCustomerResp> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SceneryCategoryListForCustomerResp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", sort=" + sort +
                ", children=" + children +
                '}';
    }
}