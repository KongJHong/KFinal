package com.hong.services.error;

import com.hong.common.error.CommonError;

/**
 * @Author : KongJHong
 * @Date : 2020-09-05 10:19
 * @Version : 1.0
 * Description     : 服务的错误类
 */
public enum ServicesError implements CommonError {
    ;

    private int errCode;

    private String errMsg;

    ServicesError(CommonError err){
        errCode = err.getErrCode();
        errMsg = err.getErrMsg();
    }

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public CommonError setErrMsg(String msg) {
        errMsg = msg;
        return this;
    }
}
