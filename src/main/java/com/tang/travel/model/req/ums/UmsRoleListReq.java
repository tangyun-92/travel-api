package com.tang.travel.model.req.ums;

import com.tang.travel.model.req.PageReq;
import io.swagger.annotations.ApiModelProperty;

public class UmsRoleListReq extends PageReq {

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "状态：0-禁用；1-启用")
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}