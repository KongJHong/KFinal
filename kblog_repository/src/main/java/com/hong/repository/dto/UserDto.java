package com.hong.repository.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-12 23:13
 * @Version : 1.0
 * Description     : user dto
 */
@Data
public class UserDto {

    private String id;

    private String username;

    private String password;

    private String avatar;

    private String introduction;

    private List<String> roles;
}
