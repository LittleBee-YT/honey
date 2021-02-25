package com.lbee.common.core;

import com.lbee.common.exception.BusinessException;
import com.lbee.common.exception.TokenCheckException;
import com.lbee.common.result.Result;
import com.lbee.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
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
            return Result.failed(ResultCode.REQUEST_METHOD_NOT, e.getMessage());
        } else if (e instanceof IllegalArgumentException) {
            return Result.failed(ResultCode.ILLEGAL_ARGUMENT, e.getMessage());
        } else if (e instanceof MissingServletRequestParameterException) {
            return Result.failed(ResultCode.ILLEGAL_ARGUMENT, e.getMessage());
        } else if (e instanceof NumberFormatException) {
            return Result.failed(ResultCode.ILLEGAL_ARGUMENT, e.getMessage());
        } else if (e instanceof TokenCheckException) {
            return Result.failed(ResultCode.UNAUTHORIZED);
//        } else if (e instanceof BusinessException) {
//            return Result.failed(((BusinessException) e).getCode(), e.getMessage());
//        } else if (e instanceof AccessDeniedException) {
//            return Result.failed(((BusinessException) e).getCode(), e.getMessage());
        } else {//其他未捕获异常
//            log.error("e.getClass:{}; exception:{}", e.getMessage(), e.getClass(), e);
            return Result.failed(ResultCode.FAILED, e.getMessage());
        }
    }
}
