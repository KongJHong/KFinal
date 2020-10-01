package com.hong.services;

import com.hong.services.consts.Consts;
import com.hong.services.services.BaseElasticService;
import com.hong.services.services.IArticleService;
import com.hong.services.services.search.ArticleTemplate;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-27 11:09
 * @Version : 1.0
 * Description     :  集成单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private BaseElasticService elasticService;

    @Autowired
    private IArticleService articleService;

    @Before
    public void doBefore() {

    }

    @After
    public void doAfter() {

    }

    @Test
    public void context() {
        try {
            System.out.println(elasticService.indexExist("xunwu"));
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void search() {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        TermQueryBuilder termBuilder = QueryBuilders.termQuery("content", "奥德赛");
        builder.query(termBuilder);

        List<ArticleTemplate> articles = elasticService.search(Consts.ARTICLE_INDEX, builder, ArticleTemplate.class);
        for (ArticleTemplate articleTemplate: articles) {
            System.out.println(articleTemplate);
        }
    }

}
