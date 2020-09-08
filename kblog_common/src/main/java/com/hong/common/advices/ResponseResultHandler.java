package com.hong.common.advices;

import com.hong.common.annotation.ResponseResult;
import com.hong.common.constant.HeaderTags;
import com.hong.common.error.CommonError;
import com.hong.common.error.CommonException;
import com.hong.common.json.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : KongJHong
 * @Date : 2020-09-08 10:47
 * @Version : 1.0
 * Description     : 切面包装
 */
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    /**
     * 请求是否 包含了 包装注解 标记，没有就直接返回，不需要重写返回体
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        // 判断请求， 是否有包装标记
        ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(HeaderTags.RESPONSE_RESULT_ANN);
        return responseResultAnn != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("进入 返回体 重写格式， 处理中 ...");
        if (body instanceof CommonException) {
            log.info(" 返回值 异常 包装类 处理中");
            CommonError errorResult = (CommonError)body;
            return JsonResult.failure(errorResult);
        }
        return JsonResult.success(body);
    }


}
