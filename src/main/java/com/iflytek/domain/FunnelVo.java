package com.iflytek.domain;

import lombok.Data;
/**
 * 漏斗图指标分析
 * */
@Data
public class FunnelVo {
    private Long present;
    private Long click; //800  点击
    private Long addCart; // 600  加购  加入购物车
    private Long order; //下单
    private Long orderAgain; //复购  下单大于1
    private Long chargeCoupon; //储值  充值的意思
}
