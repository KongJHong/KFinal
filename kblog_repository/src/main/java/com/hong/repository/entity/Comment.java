package com.hong.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-22 10:13
 * @Version : 1.0
 * Description     : 评论表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("blog_comment")
public class Comment extends AbstractBaseEntity implements Serializable {

    // 用户昵称
    String nickname;

    // 联系方式
    String contact;

    // 回复信息
    String replyContent;

    // 回复对象id 无 默认-1
    @TableField(exist = false)
    Long targetId = -1L;

    // 回复对象名称
    String targetName;

    // 子回复
    List<Comment> comments = new ArrayList<>();

    // 回复数
    Integer commentNum;

    // 点赞数
    Integer like;
}
