package com.hong.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author : KongJHong
 * @Date : 2020-09-04 16:55
 * @Version : 1.0
 * Description     : swagger 根配置类
 */
public class BaseSwaggerConfig {

    private static final String API_TITLE = "后端API";
    private static final String API_DESCRIPTION = "系统后台REST接口定义，开发桌面及移动端应用均需参考该接口规范。";
    private static final String API_LICENSE = "版权所有 西多士 有限公司";
    private static final String API_LICENSE_URL = "http://localhost";
    private static final String API_VERSION = "1.0.0.RELEASE";

    protected Docket commonApi(String groupName, String basePackage, boolean requireToken) {
        return this.buildApiCommonPart(new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage)), requireToken);
    }


    protected Docket buildApiCommonPart(ApiSelectorBuilder builder, boolean requireToken) {
        Docket docket = builder.paths(PathSelectors.any())
                .build()
                .consumes(Stream.of(MediaType.APPLICATION_JSON_UTF8_VALUE).collect(Collectors.toSet()))
                .produces(Stream.of(MediaType.APPLICATION_JSON_UTF8_VALUE).collect(Collectors.toSet()));
        return docket;
    }

    protected ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .license(API_LICENSE)
                .licenseUrl(API_LICENSE_URL)
                .version(API_VERSION)
                .build();
    }
}
