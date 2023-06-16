package com.tang.travel.model.req.ums;

import com.tang.travel.model.req.PageReq;
import io.swagger.annotations.ApiModelProperty;

public class UmsAdminListReq extends PageReq {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Integer status;

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

    @Override
    public String toString() {
        return "UmsAdminListReq{" +
                "username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", status=" + status +
                '}';
    }
}