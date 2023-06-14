package com.tang.travel.exception;

import com.tang.travel.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 校验异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ApiRestResponse validExceptionHandler(BindException e) {
        ApiRestResponse apiRestResponse = new ApiRestResponse();
        LOG.warn("参数校验失败：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        apiRestResponse.setSuccess(false);
        apiRestResponse.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return apiRestResponse;
    }

    /**
     * 校验异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ApiRestResponse validExceptionHandler(BusinessException e) {
        ApiRestResponse apiRestResponse = new ApiRestResponse();
        LOG.warn("业务异常：{}", e.getCode().getMsg());
        apiRestResponse.setSuccess(false);
        apiRestResponse.setMessage(e.getCode().getMsg());
        return apiRestResponse;
    }

    /**
     * 校验异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiRestResponse validExceptionHandler(Exception e) {
        ApiRestResponse apiRestResponse = new ApiRestResponse();
        LOG.error("系统异常：", e);
        apiRestResponse.setSuccess(false);
        apiRestResponse.setMessage("系统出现异常，请联系管理员");
        return apiRestResponse;
    }

    /**
     * 权限不足异常捕获，因为全局异常会比自定的提前捕捉到，因此直接在这里处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public ApiRestResponse handleAccessRE(AccessDeniedException e) {
        LOG.error("权限不足");
        return ApiRestResponse.error(BusinessExceptionEnum.NOT_AUTH);
    }

}
