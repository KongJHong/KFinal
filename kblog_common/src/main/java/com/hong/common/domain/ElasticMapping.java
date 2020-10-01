package com.hong.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : KongJHong
 * @Date : 2020-10-01 19:36
 * @Version : 1.0
 * Description     : yml对应es配置
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticMapping {

    private String idxName;

    private String location;
}
