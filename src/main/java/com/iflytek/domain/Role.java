package com.iflytek.domain;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private Integer id;
    private String name;
    private String sn;
    //角色维护权限集合
    private List<Permission> permissions;

}
