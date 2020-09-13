package com.hong.services;

import com.hong.services.config.RsaKeyProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author : KongJHong
 * @Date : 2020-09-04 16:04
 * @Version : 1.0
 * Description     :
 */
@SpringBootApplication
@MapperScan("com.hong.repository.mapper")
@ComponentScan(basePackages = {"com.hong"})
@EnableConfigurationProperties(RsaKeyProperties.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
