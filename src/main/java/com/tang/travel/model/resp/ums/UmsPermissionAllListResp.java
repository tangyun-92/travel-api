package com.tang.travel.model.resp.ums;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsPermissionAllListResp {
    private Integer id;

    private Integer pid;

    private String name;

    private String value;

    private Integer type;

    private String uri;

    private Integer status;

    private Date createTime;

    private Integer sort;

    private List<UmsPermissionAllListResp> children = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<UmsPermissionAllListResp> getChildren() {
        return children;
    }

    public void setChildren(List<UmsPermissionAllListResp> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "UmsPermissionAllListResp{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", type=" + type +
                ", uri='" + uri + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", sort=" + sort +
                ", children=" + children +
                '}';
    }
}