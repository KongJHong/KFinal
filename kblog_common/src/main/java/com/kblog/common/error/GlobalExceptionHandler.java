package com.kblog.common.error;

import com.kblog.common.json.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author : KongJHong
 * @Date : 2020-09-01 17:36
 * @Version : 1.0
 * Description     : 全局异常捕获
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 捕获自定义异常
    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public JsonResult handlerBusinessException(CommonException ex) {
        log.error("错误代码:{} , {}",ex.getErrCode(),ex.getErrMsg());
        ex.printStackTrace();
        return JsonResult.error(ex);
    }

    //放在最下面，捕获所有的Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
     public JsonResult doError(Exception ex){
        log.error("发生未捕获异常:",ex);
        return JsonResult.error(20001,"发生未捕获异常");
    }
}
