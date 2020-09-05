package com.hong.common.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Author : KongJHong
 * @Date : 2020-08-19 21:58
 * @Version : 1.0
 * Description     : 为了方便获取token中的用户信息，将token中在和部分单独封装成一个对象
 */
@Data
public class Payload<T> {

    private String id;

    private T userInfo;

    private Date expiration;
}
