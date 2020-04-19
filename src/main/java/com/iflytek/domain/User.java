package com.iflytek.domain;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    //用户维护角色集合
    private List<Role> roles;
}
