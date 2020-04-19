package com.iflytek.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LineVo {
    // 时间关联，线性增长的 mysql
    private String day; //时间
    private Integer regCount; //新注册用户数
    private Integer memberCount; //总用户数
    private Integer orderCount; //订单数
    private BigDecimal gmv; //GMV增长 GMV=订单数*单价
}
