package com.hong.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author : KongJHong
 * @Date : 2020-09-26 21:03
 * @Version : 1.0
 * Description     : 基础配置
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseSetting extends AbstractBaseEntity{

    /**  博客标题 */
    String blogTitle;

    /** 个人标签, 空格区分 */
    String tags;

    /** 公告 */
    String notice;

    /** 访问人数 */
    Integer views;

    /** 展示头像url */
    String avatar;
}
