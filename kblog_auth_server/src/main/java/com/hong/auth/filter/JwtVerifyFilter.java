package com.hong.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hong.auth.config.RsaKeyProperties;
import com.hong.common.domain.Payload;
import com.hong.common.error.EmCommonError;
import com.hong.common.json.JsonResult;
import com.hong.common.utils.JwtUtils;
import com.hong.repository.entity.SysUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : KongJHong
 * @Date : 2020-09-07 17:13
 * @Version : 1.0
 * Description     : Jwt验证过滤器
 */
public class JwtVerifyFilter extends BasicAuthenticationFilter {

    private RsaKeyProperties prop;

    public JwtVerifyFilter(AuthenticationManager authenticationManager, RsaKeyProperties rsaKeyProperties) {
        super(authenticationManager);
        this.prop = rsaKeyProperties;
    }

     /**
     * 照抄继承
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header == null ||!header.startsWith("Bearer ")) {
            // 如果没有认证，提示请登录
		    // 过滤器链不能落下
            chain.doFilter(request, response);
            doNotLogin(request, response);
        } else {
            // 如果携带了正确格式的token
            String token = header.replace("Bearer ","");
            // 验证token是否正确 过期
            try {
                Payload<SysUser> payload = JwtUtils.getInfoFromToken(token, prop.getPublicKey(), SysUser.class);
                SysUser user = payload.getUserInfo();
                if (user != null) {
                    UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authResult); // 获取到当前登录的用户信息并保存
                    chain.doFilter(request, response);
                }
            }catch (RuntimeException ex) {
                chain.doFilter(request, response);
                doExpired(response);
            }

        }
    }

    private void doNotLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out =  response.getWriter();
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("code", HttpServletResponse.SC_FORBIDDEN);
//        resultMap.put("msg","请登录");

        out.write(new ObjectMapper().writeValueAsString(JsonResult.error(EmCommonError.USER_NOT_LOGIN)));


        out.flush();
        out.close();
    }

    private void doExpired(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out =  response.getWriter();

        out.write(new ObjectMapper().writeValueAsString(JsonResult.error(EmCommonError.USER_LOGIN_EXPIRE)));

        out.flush();
        out.close();
    }
}
