package com.lbee.honey.auth.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lbee.common.auth.client.OauthContext;
import com.lbee.common.auth.client.OauthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component("oauthContext")
public class OauthContextImpl implements OauthContext {

    @Override
    public String getToken() {
        try {
            Object obj = SecurityContextHolder.getContext().getAuthentication().getDetails();
            JSONObject details = JSONObject.parseObject(JSONObject.toJSONString(obj));
            String token = details.getString("tokenValue");
            if (!StringUtils.isEmpty(token)) {
                return token;
            } else {
                throw new RuntimeException("tokenValue is null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token获取异常.");
        }
    }

    @Override
    public OauthUser getLoginUser() {
        try {
            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            JSONObject principal = JSONObject.parseObject(JSONObject.toJSONString(obj));
            JSONObject pl = principal.getJSONObject("principal");
            Long userId = pl.getLong("id");
            String username = pl.getString("username");
            boolean enabled = pl.getBoolean("enabled");

            JSONArray asArray = pl.getJSONArray("authorities");
            List<String> authorities = new ArrayList<>();
            asArray.forEach(item -> {
                JSONObject as = (JSONObject) item;
                authorities.add(as.getString("authority"));
            });

            OauthUser loginUser = new OauthUser();
            loginUser.setUserId(userId);
            loginUser.setUserName(username);
            loginUser.setEnabled(enabled);
            loginUser.setAuthorities(authorities);
            if (!StringUtils.isEmpty(loginUser.getUserId())) {
                return loginUser;
            } else {
                throw new RuntimeException("tokenValue is null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("用户登陆信息获取异常.");    }
}
