package com.tang.travel.exception;

/**
 * 异常枚举
 */
public enum BusinessExceptionEnum {
    PARAM_ERROR("参数错误");

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
