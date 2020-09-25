package com.hong.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hong.repository.enums.ArticleSourceEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-22 10:13
 * @Version : 1.0
 * Description     : 文章表     https://cloud.tencent.com/developer/article/1592302
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("blog_article")
public class Article extends AbstractBaseEntity implements Serializable {

    // 创建者id
    private Long userId;

    // 作者
    private String author;

    // 来源
    private ArticleSourceEnum source;

    // 来源连接
    private String sourceUrl;

    // 浏览次数
    private Integer views;

    // 文章内容
    private String content;

    // 文章标题
    private String title;

    // 文章简介
    private String description;

    /**
     * 对应标签   多对多
     */
    private List<Tag> tags;

    /**
     * 对应目录   多对一   多方
     */
    private Category category;


}
