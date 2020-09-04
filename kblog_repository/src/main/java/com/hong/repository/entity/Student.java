package com.hong.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author : KongJHong
 * @Date : 2020-09-04 11:48
 * @Version : 1.0
 * Description     :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_test_student")
public class Student extends AbstractBaseEntity implements Serializable {

    @TableField("name")
    private String name;

    private String school;

    private String city;

    // 表中不存在
    @TableField(exist = false)
    private String address;

}
