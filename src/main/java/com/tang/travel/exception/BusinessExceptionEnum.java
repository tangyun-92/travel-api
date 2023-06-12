package com.tang.travel.exception;

/**
 * 异常枚举
 */
public enum BusinessExceptionEnum {
    REQUEST_PARAM_ERROR("参数错误"),
    SYSTEM_ERROR("系统异常，请联系管理员"),
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
