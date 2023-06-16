package com.tang.travel.exception;

/**
 * 异常枚举
 */
public enum BusinessExceptionEnum {
    REQUEST_PARAM_ERROR("参数错误"),
    SYSTEM_ERROR("系统异常，请联系管理员"),
    TOKEN_LOSE_EFFICACY("暂未登录或token已过期"),
    NOT_AUTH("没有相关权限，请联系管理员"),
    WRONG_PASSWORD("用户名或密码错误"),
    MKDIR_FAILED("文件夹创建失败"),
    UPLOAD_FAILED("上传失败"),
    NAME_EXIST("该名称已存在");

    private String msg;

    BusinessExceptionEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
