package com.lbee.honey.exception;

import com.lbee.common.result.Result;
import com.lbee.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import java.io.Serializable;

@Slf4j
public class Auth2ResponseExceptionTranslator implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        log.error("授权服务器 异常统一处理:异常:{}", e.getMessage(), e);
        if (e instanceof InvalidTokenException){
            Result<Serializable> error = Result.failed(ResultCode.TOKEN_PAST);
            return new ResponseEntity(error, HttpStatus.OK);
        } else if (e instanceof UsernameNotFoundException) {
            Result<Serializable> error = Result.failed(ResultCode.LOGIN_NAME);
            return new ResponseEntity(error, HttpStatus.OK);
        } else if (e instanceof InvalidGrantException) {
            Result<Serializable> error = Result.failed(ResultCode.LOGIN_PASSWORD);
            return new ResponseEntity(error, HttpStatus.OK);
        } else if (e instanceof BadCredentialsException) {
            Result<Serializable> error = Result.failed(ResultCode.FAILED);
            return new ResponseEntity(error, HttpStatus.OK);
        }
        return new ResponseEntity(Result.failed(""), HttpStatus.OK);
    }
}
