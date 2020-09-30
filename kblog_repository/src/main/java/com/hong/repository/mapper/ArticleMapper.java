package com.hong.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hong.repository.entity.Article;
import com.hong.repository.entity.Category;
import com.hong.repository.enums.ArticleSourceEnum;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-22 10:53
 * @Version : 1.0
 * Description     : 文章映射
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("select * from blog_article article where article.id=#{article_id}")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "source", column = "source", javaType = ArticleSourceEnum.class, typeHandler = EnumOrdinalTypeHandler.class),
            @Result(property = "tags", column = "id", javaType = List.class,
            many = @Many(select="com.hong.repository.mapper.TagMapper.findByArticleId")),
            @Result(property = "category", column = "cate_id", javaType = Category.class,
            one = @One(select = "com.hong.repository.mapper.CategoryMapper.findIdAndNameById"))
    })
    Article findById(@Param("article_id") Long articleId);

    @Select("select id, title from blog_article a where a.id=#{article_id}")
    Article findIdAndTitleById(@Param("article_id") Long articleId);

    @Select("select * from blog_article article")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "source", column = "source", javaType = ArticleSourceEnum.class, typeHandler = EnumOrdinalTypeHandler.class),
            @Result(property = "tags", column = "id", javaType = List.class,
            many = @Many(select="com.hong.repository.mapper.TagMapper.findByArticleId")),
            @Result(property = "category", column = "cate_id", javaType = Category.class,
            one = @One(select = "com.hong.repository.mapper.CategoryMapper.findIdAndNameById"))
    })
    List<Article> findAll();


}
