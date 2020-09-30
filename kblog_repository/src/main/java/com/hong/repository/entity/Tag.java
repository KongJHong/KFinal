package com.hong.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-22 19:00
 * @Version : 1.0
 * Description     : 标签实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("blog_tag")
public class Tag extends AbstractBaseEntity{

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 文章列表
     */
    @TableField(exist = false)
    private List<Article> articles;


    /**
     * 浏览次数
     */
    private Integer views;


}
