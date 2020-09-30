package com.hong.services.controller;

import com.hong.common.json.JsonResult;
import com.hong.services.services.IArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : KongJHong
 * @Date : 2020-09-26 14:50
 * @Version : 1.0
 * Description     :
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final IArticleService articleService;

    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/all")
    public JsonResult getAllArticles() {
        return JsonResult.success(articleService.findAll());
    }
}
