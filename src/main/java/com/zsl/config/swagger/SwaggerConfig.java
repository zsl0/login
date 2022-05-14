package com.zsl.config.swagger;


import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;


/**
 * @Author zsl
 * @Date 2022/1/21 11:16
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig  {
    private final SwaggerProperties swaggerProperties;

    public SwaggerConfig(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)

                // api文档信息
                .apiInfo(apiInfo())

                // 分组名称
                .groupName(swaggerProperties.getApplicationVersion())

                // 定义是否开启swagger,false为关闭，可以通过yaml配置变量控制
                .enable(swaggerProperties.getEnable())

                // 选择那些接口作为swagger的doc发布
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zsl.controller"))    // api路径
                .paths(PathSelectors.any()) // 路径匹配
                .build()

                // 授权信息全局应用
                .securityContexts(securityContexts())

                // 授权信息设置，必要的header token等认证信息
                .securitySchemes(apiKeys());
    }

    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getApplicationName() + " 登录模块")
                .description(swaggerProperties.getApplicationDescription())
                .version("Application Version: " + swaggerProperties.getApplicationVersion())
                .build();
    }

    /**
     * 设置授权信息
     */
    private List<SecurityScheme> apiKeys() {
        return Lists.newArrayList(new ApiKey("Bearer Token", "Authorization", "header"));
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Lists.newArrayList(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build());
    }

    /**
     *
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("Bearer Token", authorizationScopes));
    }
}
