package com.hong.repository.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hong.repository.entity.SysUser;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-07 11:16
 * @Version : 1.0
 * Description     : 用户映射
 */
@Repository
public interface UserMapper extends BaseMapper<SysUser> {

    @Select("select * from sys_user where username=#{username}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select="com.hong.repository.mapper.RoleMapper.findByUid"))
    })
    public SysUser findByName(String username);
}
