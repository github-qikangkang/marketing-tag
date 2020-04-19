package com.iflytek.domain;

import lombok.Data;

@Data
public class MemberHeat {
    private Integer reg;//已注册
    private Integer complete;//已完善
    private Integer order;//已下单
    private Integer orderAgain;//已复购
    private Integer coupon;//已领券
}
