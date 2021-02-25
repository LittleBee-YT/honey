package com.lbee.honey.auth.client;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableOAuth2Sso
public @interface AuthClientConfiguration {

}
