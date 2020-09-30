package com.hong.services;

import com.hong.common.service.BaseElasticService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
