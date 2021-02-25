package com.lbee.common.es.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "elasticsearch")
public class EsProp {
    /**
     * 是否开启es
     */
    private Boolean enabled;
    /**
     * 使用冒号隔开ip和端口
     */
    private String[] address;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 7.X 设置为http
     */
    private String scheme;
}
