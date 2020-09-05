package com.hong.common.error;

/**
 * @Author : KongJHong
 * @Date : 2020-09-01 17:21
 * @Version : 1.0
 * Description     : 统一的错误返回接口，方便实现
 */
public interface CommonError {

    int getErrCode();

    String getErrMsg();

    CommonError setErrMsg(String msg);
}
