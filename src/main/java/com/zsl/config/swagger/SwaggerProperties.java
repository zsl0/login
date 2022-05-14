package com.zsl.config.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author zsl
 * @Date 2022/1/23 14:40
 */
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    private Boolean enable; // 是否开启swagger
    private String applicationName; // 应用程序名称
    private String applicationVersion;  // 应用程序版本
    private String applicationDescription;  // 应用程序描述
    private String tryHost; //  接口调试地址

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public String getTryHost() {
        return tryHost;
    }

    public void setTryHost(String tryHost) {
        this.tryHost = tryHost;
    }
}
