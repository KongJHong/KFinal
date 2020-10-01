package com.hong.services.services;

import com.hong.repository.dto.ArticleDto;

import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-26 14:48
 * @Version : 1.0
 * Description     :
 */
public interface IArticleService {

    List<ArticleDto> findAll();

    ArticleDto findById(Long articleId);
}
