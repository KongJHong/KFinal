package com.hong.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hong.repository.entity.SysRole;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-07 11:16
 * @Version : 1.0
 * Description     : 角色映射
 */
@Repository
public interface RoleMapper extends BaseMapper<SysRole> {

    @Select("select r.id, r.role_name roleName, r.role_desc roleDesc from sys_role r, sys_user_role ur where r.id=ur.rid and ur.uid=#{uid}")
    public List<SysRole> findByUid(Integer uid);
}
