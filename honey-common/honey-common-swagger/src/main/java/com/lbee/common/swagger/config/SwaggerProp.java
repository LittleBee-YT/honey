package com.lbee.common.swagger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
//@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProp {

    /**
     * 是否开启swagger
     */
    private Boolean enabled;

    /**
     * 标题
     **/
    private String title;
    /**
     * 作者
     **/
    private Contact contact;
    /**
     * 描述
     **/
    private String desc;
    /**
     * 版本
     **/
    private String version;
    /**
     * 服务条款
     **/
    private String termsOfServiceUrl;
    /**
     * host信息
     **/
    private String host;
    /**
     * 授权地址
     */
    private String authUri;


}



