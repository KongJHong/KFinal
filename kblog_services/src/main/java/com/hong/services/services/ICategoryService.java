package com.hong.services.services;

import com.hong.repository.entity.Category;

import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-25 14:05
 * @Version : 1.0
 * Description     : 目录接口
 */
public interface ICategoryService {

    public List<Category> findAllCategories();
}
