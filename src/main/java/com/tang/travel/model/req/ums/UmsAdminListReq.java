package com.tang.travel.model.req.ums;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class UmsAdminListReq implements Serializable {

    @ApiModelProperty(value = "用户名", name = "username", dataType = "String")
    private String username;

    @ApiModelProperty(value = "昵称", name = "nickName", dataType = "String")
    private String nickName;

    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用", name = "status", dataType = "Integer")
    private Integer status;

    @ApiModelProperty(value = "当前第几页", name = "current", dataType = "Integer")
    private Integer current = 1;

    @ApiModelProperty(value = "每页显示条数", name = "pageSize", dataType = "Integer")
    private Integer pageSize = 10;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return "UmsAdminListReq{" +
                "username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", status=" + status +
                ", current=" + current +
                ", pageSize=" + pageSize +
                '}';
    }
}