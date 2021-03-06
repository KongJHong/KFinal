package com.hong.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hong.repository.entity.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-23 21:58
 * @Version : 1.0
 * Description     : tag映射
 */
@Repository
public interface TagMapper extends BaseMapper<Tag> {

    @Select("select t.id, t.deleted, t.create_time createTime, t.update_time, t.tag_name tagName, t.views from "+
            "blog_tag t, blog_article_tag at where at.tag_id=t.id and at.article_id=#{article_id}")
    public List<Tag> findByArticleId(@Param("article_id") Long articleId);


    @Select("select * from blog_tag t, blog_article_tag bat where bat.tag_id=t.id")
    @Results(value = {
            @Result(property = "articles", column = "article_id", javaType = List.class,
                    many = @Many(select = "com.hong.repository.mapper.ArticleMapper.findIdAndTitleById"))
    })
    public List<Tag> findAll();
}
