package com.hong.common.error;

import com.hong.common.json.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
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
        return JsonResult.failure(ex);
    }

    // 捕捉SQL语法异常
    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public JsonResult handleSQLSyntaxException(BadSqlGrammarException ex) {
        log.error("SQL语法错误:", ex);
        return JsonResult.failure(EmCommonError.SQL_SYNTAX_ERROR);
    }

    //放在最下面，捕获所有的Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
     public JsonResult doError(Exception ex){
        log.error("发生未捕获异常:",ex);
        return JsonResult.failure(EmCommonError.UNKNOWN_ERROR);
    }
}
