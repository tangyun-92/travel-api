package com.tang.travel.exception;

public class BusinessException extends RuntimeException{
    private BusinessExceptionEnum code;

    public BusinessException (BusinessExceptionEnum code) {
        super(code.getMsg());
        this.code = code;
    }

    public BusinessExceptionEnum getCode() {
        return code;
    }

    public void setCode(BusinessExceptionEnum code) {
        this.code = code;
    }

    /**
     * 不写入堆栈信息，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
