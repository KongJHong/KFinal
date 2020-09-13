package com.hong.auth.config;

import com.hong.auth.filter.JwtLoginFilter;
import com.hong.auth.filter.JwtVerifyFilter;
import com.hong.auth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author : KongJHong
 * @Date : 2020-09-07 17:50
 * @Version : 1.0
 * Description     : spring security配置类
 */
@Configuration
@EnableWebSecurity // 必须加这个注解才能在这里写spring security的配置
@EnableGlobalMethodSecurity(securedEnabled = true) // 使用方法级的动态授权，这里用spring内置的授权方式
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final RsaKeyProperties prop;

    public SecurityConfig(UserService userService, RsaKeyProperties prop) {
        this.userService = userService;
        this.prop = prop;
    }

    // spring内置的加密方式
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证用户来源
     * @param auth 授权句柄
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).
                passwordEncoder(passwordEncoder()); // 加密方式
    }

    /**
     * 配置spring security分布式状况下的相关功能
     * @param http http security对象
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 释放静态资源，指定路径拦截规则，指定自定义认证页面，指定退出认证配置，csrf配置
        http.csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/product").hasAnyRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JwtLoginFilter(super.authenticationManager(), prop))
                .addFilter(new JwtVerifyFilter(super.authenticationManager(), prop))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 禁用session
    }
}
