package com.hong.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hong.repository.entity.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-23 22:05
 * @Version : 1.0
 * Description     :
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    @Select("select cate.id id, cate.category_name categoryName  from blog_category cate where id=#{id}")
    Category findIdAndNameById(@Param("id") Long id);

    @Select("select * from blog_category where id=#{id}")
    @Results(value = {
        @Result(id = true, property = "id", column = "id"),
        @Result(property = "childCategories", column = "id", javaType = List.class,
        many = @Many(select = "com.hong.repository.mapper.CategoryMapper.findAllChildByPid"))
    })
    Category findById(@Param("id") Long id);

    @Select("select * from blog_category where pid=#{pid}")
    @Results(value = {
        @Result(id = true, property = "id", column = "id"),
        @Result(property = "childCategories", column = "id", javaType = List.class,
        many = @Many(select = "com.hong.repository.mapper.CategoryMapper.findAllChildByPid"))
    })
    List<Category> findAllChildByPid(@Param("pid") Long pid);
}
