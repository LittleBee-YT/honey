package com.lbee.honey.auth.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lbee.common.auth.constant.RedisConstant;
import com.lbee.common.redis.util.RedisUtil;
import com.lbee.honey.auth.config.prop.SecurityWhitelistProp;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class AuthSecurityAccessDecisionManager implements AccessDecisionManager {

    private List<AccessDecisionVoter<? extends Object>> decisionVoters;

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private SecurityWhitelistProp securityWhitelistProp;

    @SneakyThrows
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection)
            throws AccessDeniedException, InsufficientAuthenticationException {
        String requestUri = filterId(((FilterInvocation) o).getRequest().getRequestURI());
        String methodRequestUri = ((FilterInvocation) o).getRequest().getMethod() + requestUri;
        // 过滤白名单
        if (null != securityWhitelistProp.getWhiteList()) {
            List<String> whiteList = securityWhitelistProp.getWhiteList();
            for (String whiteUri : whiteList) {
                if (methodRequestUri.equals(whiteUri)) {
                    return;
                } else {
                    int num = whiteUri.indexOf("/**");
                    if (num > -1) {
                        String baseUri = whiteUri.substring(0, num);
                        if (requestUri.indexOf(baseUri) == 0) {
                            return;
                        }
                    }
                }
            }
        }
        log.info("白名单未通过：{}", methodRequestUri);
        // 当前用户所具有的权限
        JSONArray asArray;
        try {
            JSONObject principal = JSONObject.parseObject(JSONObject.toJSONString(authentication.getPrincipal()));
            asArray = principal.getJSONArray("authorities");
        } catch (Exception e) {
            throw new AccessDeniedException("暂未登录或token已经过期");
        }
        // 从authority中拿到当前登陆用户的角色集合
        List<String> authorities = new ArrayList<>();
        asArray.forEach(item -> authorities.add(((JSONObject) item).getString("authority")));
        // 从Redis中拿出uri授权的角色集合
        redisUtil.hmget(RedisConstant.RESOURCE_ROLES_MAP);
        List<String> roleList = (List<String>) redisUtil.hget(RedisConstant.RESOURCE_ROLES_MAP, methodRequestUri);
        // 匹配授权角色
        if (null != roleList && !roleList.isEmpty()) {
            for (String as : authorities) {
                log.info("role:{}", JSONObject.toJSONString(roleList));
                if (roleList.contains(as)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("无访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        for (AccessDecisionVoter voter : this.decisionVoters) {
            if (voter.supports(configAttribute)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        for (AccessDecisionVoter voter : this.decisionVoters) {
            if (!voter.supports(aClass)) {
                return false;
            }
        }
        return false;
    }

    private String filterId(String requestUri) {
        if (requestUri.split("/").length > 1) {
            int lastIndex = requestUri.lastIndexOf("/");
            String lastUri = requestUri.substring(lastIndex + 1);
            try {
                Long.parseLong(lastUri);
                requestUri = requestUri.substring(0, lastIndex);
            } catch (Exception e) {
            }
        }
        return requestUri;
    }

//    private String filterUUID(String requestUri) {
//        if (requestUri.split("/").length > 1) {
//            int lastIndex = requestUri.lastIndexOf("/");
//            String lastUri = requestUri.substring(lastIndex + 1);
//            if (lastUri.length() == 36 || lastUri.length() == 32) {
//                requestUri = requestUri.substring(0, lastIndex);
//            }
//        }
//        return requestUri;
//    }
}
