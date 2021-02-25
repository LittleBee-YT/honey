package com.lbee.honey.auth.config;

import com.lbee.honey.auth.handler.AuthenticationFailureHandler;
import com.lbee.honey.auth.handler.CustomAccessDeniedHandler;
import com.lbee.honey.auth.handler.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String secret;

    @Value("${security.oauth2.authorization.check-token-access}")
    private String checkTokenEndpointUrl;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Resource
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Resource
    private AuthSecurityAccessDecisionManager accessDecisionManager; //权限判断

    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Resource
    private CustomAuthenticationEntryPoint authExceptionEntryPoint;

    @Bean
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setClientId(clientId);
        tokenService.setClientSecret(secret);
        tokenService.setCheckTokenEndpointUrl(checkTokenEndpointUrl);

//        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
//        defaultAccessTokenConverter.setUserTokenConverter(new AuthUserAuthenticationConverter());
//        tokenService.setAccessTokenConverter(defaultAccessTokenConverter);
        return tokenService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().failureHandler(authenticationFailureHandler)
                .and()
                .authorizeRequests().withObjectPostProcessor(authObjectPostProcessor())
                .and()
                .authorizeRequests().regexMatchers().permitAll().anyRequest().authenticated()
                .and()
                .httpBasic();
//        http
//                .csrf().disable()
////                .exceptionHandling().authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
////                .and() // 另外，如果不设置，那么在通过浏览器访问被保护的任何资源时，每次是不同的SessionID，并且将每次请求的历史都记录在OAuth2Authentication的details的中
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
////                .and() // httpSecurity 放过健康检查，其他都需要验证  设置了.anyRequest().authenticated()才回进入自定义的权限判断
//                .formLogin().failureHandler(authenticationFailureHandler)
//                .and() // 白名单 这里做配置，业务中的白名单url放入到权限判断中去
//                .authorizeRequests().antMatchers().permitAll().anyRequest().authenticated()
////                .and() // .requestMatchers().antMatchers(...) OAuth2设置对资源的保护如果是用 /**的话 会把上面的也拦截掉
////                .requestMatchers().antMatchers("/auth/**","/user/**")
////                .requestMatchers().antMatchers("/web/user/**").permitAll()
//
//                .and() // 重写做权限判断
//                .authorizeRequests().withObjectPostProcessor(authObjectPostProcessor())
//                .and()
//                .httpBasic();
    }

    public ObjectPostProcessor<FilterSecurityInterceptor> authObjectPostProcessor() {
        return new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setAccessDecisionManager(accessDecisionManager); // 权限判断
                return o;
            }
        };
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(authExceptionEntryPoint)
                .tokenServices(tokenService()).stateless(true);
    }
}
