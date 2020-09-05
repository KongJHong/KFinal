package com.hong.services;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
