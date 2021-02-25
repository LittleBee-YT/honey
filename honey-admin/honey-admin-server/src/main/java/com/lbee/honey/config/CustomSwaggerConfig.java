package com.lbee.honey.config;

import com.lbee.common.swagger.config.SwaggerProp;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 配置
 */
@Configuration
@EnableSwagger2
//@EnableKnife4j
//@Import(BeanValidatorPluginsConfiguration.class)
@ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true)
public class CustomSwaggerConfig {

    @Bean
    @ConditionalOnMissingBean
    public SwaggerProp swaggerProp() {
        return new SwaggerProp();
    }

    @Bean
    public Docket sysApi(SwaggerProp swaggerProp) {
        // 构造函数传入初始化规范，这是swagger2规范
        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerProp.getHost())
                .apiInfo(apiInfo(swaggerProp))
                .groupName("SYS")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lbee.honey.sys.controller"))
                .paths(PathSelectors.any())
                .build();
        //.securitySchemes(securitySchemes())
        //.securityContexts(securityContexts());
    }

    private ApiInfo apiInfo(SwaggerProp properties) {
        return new ApiInfoBuilder()
                .title(properties.getTitle()) // 标题
                .description(properties.getDesc()) // 描述
                //作者
                .contact(new Contact(properties.getContact().getName(),
                        properties.getContact().getUrl(),
                        properties.getContact().getEmail()))
                //服务条款网址
                .termsOfServiceUrl(properties.getTermsOfServiceUrl()) // 链接
                .version(properties.getVersion()) // 版本
                .build();
    }

//
//    private List<ApiKey> securitySchemes() {
//        return Lists.newArrayList(
//                new ApiKey("Authorization", "Authorization", "header"));
//    }
//
//    @Bean
//    public SecurityScheme oauth(SwaggerProps sp) {
//        return new OAuthBuilder()
//                .name("OAuth2")
//                .scopes(scopes())
//                .grantTypes(grantTypes(sp))
//                .build();
//    }
//
//    private List<AuthorizationScope> scopes() {
//        return Lists.newArrayList(new AuthorizationScope("app", "Grants openid access"));
//    }
//
//    public List<GrantType> grantTypes(SwaggerProps properties) {
//        List<GrantType> grantTypes = new ArrayList<>();
//        grantTypes.add(new ResourceOwnerPasswordCredentialsGrant(properties.getAuthUri()));
//        return grantTypes;
//    }
//
//    private List<SecurityContext> securityContexts() {
//        return Lists.newArrayList(
//                SecurityContext.builder()
//                        .securityReferences(defaultAuth())
//                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
//                        .build()
//        );
//    }
//
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Lists.newArrayList(
//                new SecurityReference("Authorization", authorizationScopes));
//    }
//
}