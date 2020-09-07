package com.hong.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Author : KongJHong
 * @Date : 2020-09-07 11:03
 * @Version : 1.0
 * Description     : 角色
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRole extends AbstractBaseEntity implements GrantedAuthority {

    private String roleName;

    private String roleDesc;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return roleName;
    }
}
