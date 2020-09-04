package com.kblog.common.json;

import com.kblog.common.error.CommonError;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author : KongJHong
 * @Date : 2020-09-01 17:31
 * @Version : 1.0
 * Description     : 统一的json返回格式
 */
@Getter
@Setter
public class JsonResult<T> {

    private T data;

    private int code;

    private String msg;

    private JsonResult(){}

    private JsonResult(int code, String msg) {
        this(code, msg, null);
    }

    private JsonResult(Integer code,String msg,T jsonData){
        this.code = code;
        this.msg = msg;
        this.data = jsonData;
    }

     public static<T> JsonResult ok(CommonError commonError){
        return data(commonError,null);
    }

    public static<T> JsonResult error(CommonError commonError){
        return data(commonError,null);
    }

    public static<T> JsonResult error(Integer code,String msg){
        return new JsonResult(code,msg);
    }

    public static<T> JsonResult data(CommonError commonError,T jsonData){
        JsonResult jsonResult = new JsonResult();
        jsonResult.msg = commonError.getErrMsg();
        jsonResult.code = commonError.getErrCode();
        jsonResult.data = jsonData;
        return jsonResult;
    }

}
