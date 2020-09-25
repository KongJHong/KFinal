package com.hong.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;

import java.util.List;
import java.util.Set;

/**
 * @Author : KongJHong
 * @Date : 2020-09-22 23:28
 * @Version : 1.0
 * Description     : 目录
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Category extends AbstractBaseEntity {


    /**
     * 目录名称
     */
    private String categoryName;

    /**
     * 子目录列表
     */
    private List<Category> childCategories;

    /**
     * 目录下的文章
     */
    @TableField(exist = false)
    private Set<Article> articles;


}
