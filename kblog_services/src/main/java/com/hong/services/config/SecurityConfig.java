package com.hong.services.config;

import com.hong.services.consts.Consts;
import com.hong.services.filter.JwtVerifyFilter;
import com.hong.services.filter.ReWriteAccessDenyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @Author : KongJHong
 * @Date : 2020-08-16 15:27
 * @Version : 1.0
 * Description     : spring security配置类
 */
@Configuration
@EnableWebSecurity // 必须加这个注解才能在这里写spring security的配置
@EnableGlobalMethodSecurity(securedEnabled = true) // 使用方法级的动态授权，这里用spring内置的授权方式
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final private RsaKeyProperties prop;

    final private ReWriteAccessDenyFilter reWriteAccessDenyFilter;


    public SecurityConfig(RsaKeyProperties prop, ReWriteAccessDenyFilter reWriteAccessDenyFilter) {
        this.prop = prop;
        this.reWriteAccessDenyFilter = reWriteAccessDenyFilter;
    }

    // 配置spring security相关信息   分布式
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 释放静态资源，指定路径拦截规则， 指定自定义认证页面，指定退出认证配置，csrf配置
         http.csrf()
                 .disable()
                 .authorizeRequests()
                 .antMatchers(Consts.whiteList).anonymous()  // 前端页面，不拦截
                 .antMatchers("/admin/**").hasRole("ADMIN")
                 .anyRequest()
                 .authenticated()
                 .and()
                 .addFilter(new JwtVerifyFilter(super.authenticationManager(), prop))
                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 禁用session
         http.exceptionHandling().accessDeniedHandler(reWriteAccessDenyFilter);
    }

}
