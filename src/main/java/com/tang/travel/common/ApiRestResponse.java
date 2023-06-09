package com.tang.travel.common;

import com.tang.travel.exception.BusinessExceptionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通用返回对象
 */
@ApiModel(value = "result", description = "返回值对象")
public class ApiRestResponse<T> {

    private Boolean success;

    @ApiModelProperty(value = "信息提示", name = "msg", dataType = "Integer")
    private String message;

    @ApiModelProperty(value = "返回数据", name = "data", dataType = "T")
    private T data;

    private static final String OK_MSG = "SUCCESS";
    private static final Boolean OK_SUCCESS = true;

    public ApiRestResponse(String msg, T data) {
        this.message = msg;
        this.data = data;
    }

    public ApiRestResponse(Boolean success, String msg) {
        this.message = msg;
        this.success = success;
    }

    public ApiRestResponse() {
        this(OK_SUCCESS, OK_MSG);
    }

    public static <T> ApiRestResponse<T> success() {
        return new ApiRestResponse<>();
    }

    public static <T> ApiRestResponse<T> success(T result) {
        ApiRestResponse<T> response = new ApiRestResponse<>();
        response.setData(result);
        return response;
    }

    public static <T> ApiRestResponse error(BusinessExceptionEnum ex) {
        return new ApiRestResponse<>(false, ex.getMsg());
    }

    public static ApiRestResponse error(Boolean success, String message) {
        return new ApiRestResponse(false, message);
    }

    @Override
    public String toString() {
        return "ApiRestResponse{" +
                ", msg='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
