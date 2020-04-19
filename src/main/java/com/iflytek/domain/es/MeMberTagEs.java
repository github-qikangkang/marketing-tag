package com.iflytek.domain.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Data
@Document(indexName = "tags",type = "_doc",
        useServerConfiguration = true,createIndex = false)
public class MeMberTagEs {
    @Id
    @Field(type = FieldType.Auto)
    private Integer memberId;
    @Field(type = FieldType.Auto)
    private String phone;
    @Field(type = FieldType.Auto)
    private String sex;
    @Field(type = FieldType.Auto)
    private Integer channel;
    @Field(type = FieldType.Auto)
    private String subOpenId;
    /*@Field(type = FieldType.Auto)
    private Integer address;*/
    @Field(type = FieldType.Auto)
    private String regTime;
    // i_member.t_member
    // i_order
    @Field(type = FieldType.Auto)
    private Integer orderCount;
    // max(create_time) i_order.t_order
    @Field(type = FieldType.Auto)
    private String orderTime;
    @Field(type = FieldType.Auto)
    private Float orderMoney;
    @Field(type = FieldType.Auto)
    private List<Integer> favGoods;
    // i_order

    // i_marketing
    @Field(type = FieldType.Auto)
    private String freeCouponTime;

    @Field(type = FieldType.Auto)
    private List<Date> couponTimes;
    @Field(type = FieldType.Auto)
    private Float chargeMoney;
    // i_marketing
    @Field(type = FieldType.Auto)
    private Integer overTime;
    @Field(type = FieldType.Auto)
    private Integer feedBack;
}
