package com.hong.services.services.impl;

import com.hong.repository.entity.Category;
import com.hong.repository.mapper.CategoryMapper;
import com.hong.services.services.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-25 14:06
 * @Version : 1.0
 * Description     : 目录接口的实现
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryMapper.findAllChildByPid(0L);
    }
}
