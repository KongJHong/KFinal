package com.hong.services.config;

import com.hong.common.config.BaseSwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author : KongJHong
 * @Date : 2020-09-04 17:37
 * @Version : 1.0
 * Description     :  当前服务的swagger
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig extends BaseSwaggerConfig {

    private static final String API_KFINAL_PACKAGE = "com.hong.%s.controller";

    @Bean
    public Docket restfulAPI() {
        return this.commonApi("KFinal接口", String.format(API_KFINAL_PACKAGE, "services"), false);
    }
}
