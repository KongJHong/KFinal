package com.hong.services.services.search;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : KongJHong
 * @Date : 2020-10-01 17:53
 * @Version : 1.0
 * Description     : 索引结构模板
 */
@Data
public class ArticleTemplate {

   private Long id;

   private String title;

   private String content;

   private String description;

   private LocalDateTime createTime;

   private LocalDateTime updateTime;
}
