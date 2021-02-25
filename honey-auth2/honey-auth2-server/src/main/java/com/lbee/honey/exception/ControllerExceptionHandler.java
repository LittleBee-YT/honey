package com.lbee.honey.exception;

import com.lbee.common.exception.BusinessException;
import com.lbee.common.result.Result;
import com.lbee.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handler(Exception e) {
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return Result.failed(ResultCode.LOGIN_ERROR, e.getMessage());
        } else if (e instanceof IllegalArgumentException) {
            return Result.failed(ResultCode.LOGIN_ERROR, e.getMessage());
        } else if (e instanceof MissingServletRequestParameterException) {
            return Result.failed(ResultCode.LOGIN_ERROR, e.getMessage());
        } else if (e instanceof NumberFormatException) {
            return Result.failed(ResultCode.LOGIN_ERROR, e.getMessage());
        } else if (e instanceof InternalAuthenticationServiceException) {
            return Result.failed(ResultCode.LOGIN_ERROR, e.getMessage());
        } else if (e instanceof BusinessException) {
//            return Result.failed(((BusinessException) e).getCode(), e.getMessage());
            return Result.failed(ResultCode.FAILED, e.getMessage());
//        } else if (e instanceof InvalidGrantException) {
//            return Result.failed( e.getMessage());
        } else {//其他未捕获异常
            e.printStackTrace();
            return Result.failed(ResultCode.LOGIN_ERROR, "服务器内部错误,请联系系统管理员!");
        }
    }
}
