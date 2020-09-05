package com.hong.common.error;

/**
 * @Author : KongJHong
 * @Date : 2020-09-01 17:23
 * @Version : 1.0
 * Description     : 自定义异常类(运行时)   装饰者模式
 */
public class CommonException extends RuntimeException implements CommonError {

    private CommonError commonError;

    public CommonException(CommonError commonError) {
        super();  // 不能忘，这是RuntimeException的无参构造
        this.commonError = commonError;
    }

    public CommonException(CommonError commonError, String msg) {
        super();
        this.commonError = commonError;
        setErrMsg(msg);
    }


    @Override
    public int getErrCode() {
        return commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String msg) {
        return commonError.setErrMsg(msg);
    }
}
