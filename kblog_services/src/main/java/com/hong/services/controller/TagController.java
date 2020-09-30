package com.hong.services.controller;

import com.hong.common.json.JsonResult;
import com.hong.services.services.ITagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : KongJHong
 * @Date : 2020-09-26 17:36
 * @Version : 1.0
 * Description     :
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    private final ITagService tagService;

    public TagController(ITagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/all")
    public JsonResult findAll() {
        return JsonResult.success(tagService.findAll());
    }
}
