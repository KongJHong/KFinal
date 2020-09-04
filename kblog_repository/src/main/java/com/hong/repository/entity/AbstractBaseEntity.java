package com.hong.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : KongJHong
 * @Date : 2020-09-04 11:57
 * @Version : 1.0
 * Description     : 抽象基础类 每个数据库表必须要有的字段
 */
@Data
abstract class AbstractBaseEntity {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic  // 逻辑删除
    private Integer deleted;


}
