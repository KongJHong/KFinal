package com.hong.auth.controller;

import com.hong.common.annotation.ResponseResult;
import com.hong.common.json.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : KongJHong
 * @Date : 2020-08-16 14:15
 * @Version : 1.0
 * Description     :
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @RequestMapping("/findAll")
    public String findAll() {
        return "产品列表查询成功";
    }

}
