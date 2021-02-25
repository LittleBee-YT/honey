package com.lbee.honey.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lbee.common.exception.BusinessException;
import com.lbee.common.result.Result;
import com.lbee.common.result.ResultCode;
import com.lbee.honey.auth.entity.Oauth2Token;
import com.lbee.honey.sys.entity.User;
import com.lbee.honey.sys.service.RoleService;
import com.lbee.honey.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌接口
 */
@Slf4j
@RestController
@RequestMapping("/oauth")
@Api(value = "oauth", tags = "认证服务")
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Resource
    private TokenStore tokenStore;

    @ApiOperation("登录")
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Result<Oauth2Token> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters, HttpServletRequest request)
            throws HttpRequestMethodNotSupportedException {
        // 非空验证
        String password = parameters.get("password");
        String username = parameters.get("username");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new BusinessException(ResultCode.LOGIN_NAME.getCode(), ResultCode.LOGIN_NAME.getMessage());
        }

        // 按loginName or email or phone 匹配查询user是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(User::getUsername, username)
                .or().eq(User::getEmail, username)
                .or().eq(User::getMobile, username);
        User user = userService.getOne(queryWrapper);
        log.info(JSONObject.toJSONString(user));
        if (null == user) {
            throw new BusinessException(ResultCode.LOGIN_PASSWORD.getCode(), ResultCode.LOGIN_PASSWORD.getMessage());
        }
        // 按平台密码加密规则覆盖parameters
//        try {
//            String md5Pwd = PasswordUtils.md5With(password, username);
//            parameters.put("password", md5Pwd);
//            parameters.put("username", user.);
//        } catch (Exception e) {
//            log.error("密码加密失败. e:{}", e.getMessage());
//            throw new BusinessException(ResultEnum.LOGIN_ERROR.getCode(), ResultEnum.LOGIN_ERROR.getDesc());
//        }
        // 交给OAuth2处理
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();

        Oauth2Token oauth2Token = Oauth2Token.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead("Bearer ").build();

        return Result.success(oauth2Token);

    }

    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices consumerTokenServices;

    @RequestMapping(value = "/token", method = RequestMethod.DELETE)
    @ResponseBody
    public Result revokeToken(String access_token, HttpServletRequest request) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(access_token);
        return Result.success();
    }


    /**
     * 刷新服务缓存
     */
    @ApiOperation("刷新缓存")
    @RequestMapping(value = "/cacheRoleMenuLoad", method = RequestMethod.POST)
    public void cacheRoleMenuLoad() {
//        sysRoleService.cacheRoleMenu();

    }

}
