package com.iflytek.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;
@Data
public class MemberTag{
    // i_member.t_member
    private String memberId;
    private String phone;
    private String sex;
    private String channel;
    private String subOpenId;
    private String address;
    private String regTime;
    // i_member.t_member
    // i_order
    private Long orderCount;
    // max(create_time) i_order.t_order
    private String orderTime;
    private Double orderMoney;
    private List<String> favGoods;
    // i_order
    // i_marketing
    private String freeCouponTime;
    private List<String> couponTimes;
    private Double chargeMoney;
    // i_marketing
    private Integer overTime;
    private Integer feedBack;
}
