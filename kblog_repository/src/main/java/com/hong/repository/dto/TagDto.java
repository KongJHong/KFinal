package com.hong.repository.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hong.repository.entity.Article;
import com.hong.repository.entity.Tag;
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
public class TagDto {

    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String tagName;

    private List<ArticleDto> articles = new ArrayList<>();

    private Integer articlesNum;

    private Integer views;

    public TagDto(@NotNull Tag tag) {
        if (tag == null)return;
        this.id = String.valueOf(tag.getId());
        this.tagName = tag.getTagName();
        this.views = tag.getViews();
        this.updateTime = tag.getUpdateTime();
        this.createTime = tag.getCreateTime();

        if (tag.getArticles() != null && tag.getArticles().size() > 0) {
            for (Article article : tag.getArticles()) {
                this.articles.add(new ArticleDto(article));
            }
        }

        this.articlesNum = this.articles.size();
    }

}
