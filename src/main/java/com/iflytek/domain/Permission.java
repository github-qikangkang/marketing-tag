package com.iflytek.domain;

import lombok.Data;

@Data
public class Permission {
    private Integer id;
    private String name;
    private String resource;
}
