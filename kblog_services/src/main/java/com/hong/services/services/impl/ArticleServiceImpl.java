package com.hong.services.services.impl;

import com.hong.repository.dto.ArticleDto;
import com.hong.repository.entity.Article;
import com.hong.repository.mapper.ArticleMapper;
import com.hong.services.services.IArticleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-26 14:49
 * @Version : 1.0
 * Description     :
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public List<ArticleDto> findAll() {
        List<ArticleDto> dtos = new ArrayList<>();
        List<Article> articles = articleMapper.findAll();

        if (articles != null && articles.size() > 0) {
            for (Article article: articles) {
                dtos.add(new ArticleDto(article));
            }
        }

        return dtos;
    }
}
