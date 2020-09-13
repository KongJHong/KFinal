package com.hong.services.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hong.common.error.EmCommonError;
import com.hong.common.json.JsonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : KongJHong
 * @Date : 2020-09-08 14:45
 * @Version : 1.0
 * Description     : 重写角色拒绝访问
 */
@Component
public class ReWriteAccessDenyFilter  implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(JsonResult.failure(EmCommonError.USER_ACCESS_DENIES)));
    }
}
