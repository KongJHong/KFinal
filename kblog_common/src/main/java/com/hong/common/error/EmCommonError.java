package com.hong.common.error;

/**
 * @Author : KongJHong
 * @Date : 2020-09-01 17:27
 * @Version : 1.0
 * Description     : 枚举错误类型和信息
 */
public enum EmCommonError implements CommonError  {
    SUCCESS(1, "成功"),
    USER_LOGIN_SUCCESS(200, "登录成功"),
    USER_NOT_LOGIN(2001, "未登录用户"),
    USER_LOGIN_ERROR(2002, "账户不存在或密码错误"),
    USER_LOGIN_EXPIRE(2003, "账户过期，请重新登录"),
    LOGIN_TOO_MANY_ERROR(10001,"登录次数过多"),
    VERIFY_CODE_EXPIRE_ERROR(10002,"验证码已过期！"),
    ;

    private int errCode;

    private String errMsg;

    EmCommonError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
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
