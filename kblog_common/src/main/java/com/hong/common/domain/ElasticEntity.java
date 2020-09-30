package com.hong.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : KongJHong
 * @Date : 2020-09-29 16:15
 * @Version : 1.0
 * Description     : 数据存储对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticEntity {

    /**
     * 主键标识，用户ES持久化
     */
    private String id;

    /**
     * JSON对象，实际存储数据
     */
    private Map<String, Object> data = new HashMap();
}
