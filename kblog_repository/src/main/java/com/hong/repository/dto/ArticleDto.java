package com.hong.repository.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hong.repository.entity.Article;
import com.hong.repository.entity.Tag;
import com.hong.repository.enums.ArticleSourceEnum;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-26 14:52
 * @Version : 1.0
 * Description     :
 */
@Data
@NoArgsConstructor
public class ArticleDto {

    private String id;

    // 作者
    private String author;

    private String coverUrl;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private List<TagDto> tags = new ArrayList<>();

    private CategoryDto category;


    public ArticleDto(@NotNull Article article) {
        if (article == null)return;
        this.id = String.valueOf(article.getId());
        this.author = article.getAuthor();
        this.source = article.getSource();
        this.sourceUrl = article.getSourceUrl();
        this.views = article.getViews();
        this.content = article.getContent();
        this.title = article.getTitle();
        this.coverUrl = article.getCoverUrl();
        this.description = article.getDescription();
        this.createTime = article.getCreateTime();
        this.updateTime = article.getUpdateTime();

        this.category = new CategoryDto(article.getCategory());

        if (article.getTags() != null && article.getTags().size() > 0) {
            for (Tag tag: article.getTags()) {
                this.tags.add(new TagDto(tag));
            }
        }
    }
}
