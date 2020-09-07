package com.hong.auth;

import com.hong.auth.config.RsaKeyProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author : KongJHong
 * @Date : 2020-09-07 11:34
 * @Version : 1.0
 * Description     :
 */
@SpringBootApplication
@MapperScan("com.hong.repository.mapper")
@ComponentScan(basePackages = {"com.hong"})
@EnableConfigurationProperties(RsaKeyProperties.class)
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
