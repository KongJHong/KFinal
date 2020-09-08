package com.hong.common.json;

import com.hong.common.error.CommonError;
import com.hong.common.error.EmCommonError;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import java.io.Serializable;

/**
 * @Author : KongJHong
 * @Date : 2020-09-01 17:31
 * @Version : 1.0
 * Description     : 统一的json返回格式
 */
@Getter
@Setter
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

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

    /**
     * 名称不清晰，不建议使用，建议用success或failure
     */
    @Deprecated
    public static<T> JsonResult ok(CommonError commonError){
        return data(commonError,null);
    }

    public static<T> JsonResult success(@Nullable T data){
        return data(EmCommonError.SUCCESS,data);
    }

    public static<T> JsonResult failure(CommonError commonError){
        return data(commonError,null);
    }

    public static<T> JsonResult failure(CommonError commonError, @Nullable T jsonData){
        return data(commonError,jsonData);
    }

    private static<T> JsonResult data(CommonError commonError, @Nullable T jsonData){
        JsonResult jsonResult = new JsonResult();
        jsonResult.msg = commonError.getErrMsg();
        jsonResult.code = commonError.getErrCode();
        jsonResult.data = jsonData;
        return jsonResult;
    }

}
