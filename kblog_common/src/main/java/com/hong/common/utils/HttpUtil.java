package com.hong.common.utils;

/**
 * @Author : KongJHong
 * @Date : 2020-09-25 9:58
 * @Version : 1.0
 * Description     : http工具
 */
public class HttpUtil {

    /** 验证页面是不是不需要token */
    public static boolean isWhiteList(String url, String ...list) {
        for(String path: list) {
            String result = path.replaceAll("/\\*\\*", "");
            if (url.startsWith(result))return true;
        }
        return false;
    }
}
