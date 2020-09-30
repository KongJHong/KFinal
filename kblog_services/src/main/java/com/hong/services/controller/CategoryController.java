package com.hong.services.controller;

import com.hong.common.json.JsonResult;
import com.hong.repository.dto.CategoryDto;
import com.hong.repository.entity.Category;
import com.hong.services.services.ICategoryService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-25 14:04
 * @Version : 1.0
 * Description     : 目录controller
 */
@RestController
@RequestMapping("/cate")
@Api(tags = "目录")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public JsonResult getAllCategories() {
        List<Category> categories = categoryService.findAllCategories();
        return JsonResult.success(do2dto(categories));
    }



    private List<CategoryDto> do2dto(List<Category> categories) {
        List<CategoryDto> dtos = new ArrayList<>();
        for (Category cate: categories) {
            dtos.add(new CategoryDto(cate));
        }
        return dtos;
    }

}
