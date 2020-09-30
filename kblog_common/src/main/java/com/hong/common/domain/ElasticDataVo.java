package com.hong.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : KongJHong
 * @Date : 2020-09-29 16:17
 * @Version : 1.0
 * Description     : http交互VO对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElasticDataVo {

    /**
     * 索引名
     */
    private String idxName;

    /**
     * 数据存储对象
     */
    private ElasticEntity elasticEntity;
}
