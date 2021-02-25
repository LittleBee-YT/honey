package com.lbee.honey.auth.handler;

import com.alibaba.fastjson.JSONObject;
import com.lbee.common.result.Result;
import com.lbee.common.result.ResultCode;
import io.netty.util.CharsetUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义Token异常处理
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("{},匿名用户访问无权限:{}",request.getRequestURI(), authException.getMessage());
        response.setCharacterEncoding(CharsetUtil.UTF_8.toString());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.OK.value());
        response.getWriter()
                .print(JSONObject.toJSONString(Result.failed(ResultCode.UNAUTHORIZED)));

    }
}
