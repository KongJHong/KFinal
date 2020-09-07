package com.hong.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-07 11:03
 * @Version : 1.0
 * Description     : 用户 对象 必须实现UserDetails接口
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends AbstractBaseEntity implements UserDetails {

    private String username;

    private String password;

    private Integer status;

    private List<SysRole> roles;

    /**
     * 必须要知道，如果要进行json转换，接口实现的方法应该是不需要返回的，特别是在分布式环境中
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getDeleted() == 0;
    }
}
