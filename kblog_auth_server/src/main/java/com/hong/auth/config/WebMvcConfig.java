package com.hong.auth.config;

import com.hong.auth.interceptor.CorsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : KongJHong
 * @Date : 2020-09-08 11:42
 * @Version : 1.0
 * Description     :  MVC配置
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private CorsInterceptor corsInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyStringConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        List<StringHttpMessageConverter> converterList = converters.stream()
                .filter(converter -> converter.getClass().equals(StringHttpMessageConverter.class))
                .map(converter -> (StringHttpMessageConverter)converter).collect(Collectors.toList());

        if (converterList.isEmpty())converters.add(responseBodyStringConverter());
        else converterList.forEach(converter -> converter.setDefaultCharset(StandardCharsets.UTF_8));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//         registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
          // 校验token的拦截器
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/admin/**");
    }
}
