package com.hong.common.annotation;

import java.lang.annotation.*;

/**
 * @Author : KongJHong
 * @Date : 2020-09-08 10:25
 * @Version : 1.0
 * Description     : 标记返回值是否需要包装
 */
@Retention(RetentionPolicy.RUNTIME)              // 运行时
@Target({ElementType.TYPE, ElementType.METHOD})  // 方法和类型上
@Documented
public @interface ResponseResult {



}
