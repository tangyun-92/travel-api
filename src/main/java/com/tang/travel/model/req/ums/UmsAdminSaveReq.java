package com.tang.travel.model.req.ums;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

public class UmsAdminSaveReq {

    @ApiModelProperty(value = "id")
    private Long id;

    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", name = "username", dataType = "String", required = true)
    private String username;

    @ApiModelProperty(value = "头像", name = "icon", dataType = "String")
    private String icon;

    @ApiModelProperty(value = "邮箱", name = "email", dataType = "String")
    private String email;

    @NotEmpty(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称", name = "nickName", dataType = "String", required = true)
    private String nickName;

    @ApiModelProperty(value = "备注信息", name = "note", dataType = "String")
    private String note;

    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用", name = "status", dataType = "String")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
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
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", icon=").append(icon);
        sb.append(", email=").append(email);
        sb.append(", nickName=").append(nickName);
        sb.append(", note=").append(note);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}