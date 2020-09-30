package com.hong.services.consts;

/**
 * @Author : KongJHong
 * @Date : 2020-09-25 9:53
 * @Version : 1.0
 * Description     : 常量数据
 */
public class Consts {

    /** 前端页面，不拦截 */
    public static final String[] whiteList = new String[]
            {
                    "/test/**", "/cate/**",
                    "/article/**", "/tag/**"
            };
}
