package com.hong.repository;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author : KongJHong
 * @Date : 2020-09-04 11:16
 * @Version : 1.0
 * Description     :
 */
@SpringBootApplication
@MapperScan("com.hong.repository.mapper")
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
