package com.newland.upload.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "ly.upload")
public class UploadProperties {

    private String baseUrl;

    private List<String> allowType;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<String> getAllowType() {
        return allowType;
    }

    public void setAllowType(List<String> allowType) {
        this.allowType = allowType;
    }
}
