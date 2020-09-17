package com.hong.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hong.auth.config.RsaKeyProperties;
import com.hong.common.annotation.ResponseResult;
import com.hong.common.error.CommonException;
import com.hong.common.error.EmCommonError;
import com.hong.common.json.JsonResult;
import com.hong.common.utils.JwtUtil;
import com.hong.repository.dto.UserDto;
import com.hong.repository.entity.SysRole;
import com.hong.repository.entity.SysUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : KongJHong
 * @Date : 2020-09-07 11:58
 * @Version : 1.0
 * Description     : 登录认证过滤器
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private RsaKeyProperties prop;

    // 谁要用，谁传过来
    public JwtLoginFilter(AuthenticationManager authenticationManager,RsaKeyProperties prop) {
        this.authenticationManager = authenticationManager;
        this.prop = prop;
    }

     /**
     * 认证逻辑！
     * 直接点进去被继承的方法看里面的逻辑，目的就是不想让他通过form表单去判断账户密码，所以绝对不能调用super.attemptAuthentication(request, response);
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        return super.attemptAuthentication(request, response);

        try (InputStream is= request.getInputStream()){
            SysUser sysUser = new ObjectMapper().readValue(is, SysUser.class);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    sysUser.getUsername(), sysUser.getPassword());
            return authenticationManager.authenticate(authRequest);
        } catch (Exception e) {
            // TODO 返回改进
           try {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                PrintWriter out =  response.getWriter();
                out.write(new ObjectMapper().writeValueAsString(JsonResult.failure(EmCommonError.USER_LOGIN_ERROR)));
                out.flush();
                out.close();
            }catch(Exception outEx) {
//                outEx.printStackTrace();
            }

           // 返回错误信息

//            throw new RuntimeException(e);
            throw new CommonException(EmCommonError.USER_LOGIN_ERROR);
        }
    }


    @Override
    @SuppressWarnings("unchecked")
    @ResponseResult
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(authResult.getName());
        sysUser.setRoles((List<SysRole>) authResult.getAuthorities());
        String token = JwtUtil.generateTokenExpireInMinutes(sysUser, prop.getPrivateKey(), 24 * 60);
        response.addHeader("Authorization", "Bearer " + token);

        try(PrintWriter out =  response.getWriter()) {
            // TODO 返回改为Json
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            out.write(new ObjectMapper().writeValueAsString(JsonResult.success(cultivateUserDto(sysUser))));
            out.flush();
        }catch(Exception outEx) {
            outEx.printStackTrace();
        }
    }

    private UserDto cultivateUserDto(SysUser sysUser) {
        UserDto userDto = new UserDto();
//        userDto.setId(sysUser.getId().toString());
        userDto.setUsername(sysUser.getUsername());
        userDto.setRoles(sysUser.getRoles().stream().map(SysRole::getRoleName).collect(Collectors.toList()));
        return userDto;
    }

}
