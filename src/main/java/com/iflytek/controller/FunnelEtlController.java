package com.iflytek.controller;

import com.iflytek.domain.FunnelVo;
import com.iflytek.mapper.FunnelEtlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//订单 复购 充值指标处理
@Controller
public class FunnelEtlController {
    @Autowired
    private FunnelEtlMapper funnelEtlMapper;
    @RequestMapping("/funnel")
    @ResponseBody
    public FunnelVo funnel(){
        FunnelVo vo = funnelEtlMapper.getOrderMemberCount();
        vo.setPresent(1000L);
        vo.setClick(800L);
        vo.setAddCart(600L);
        Long orderAgain = funnelEtlMapper.getOrderAgainMember();
        vo.setOrderAgain(orderAgain);
        Long chargeCoupon = funnelEtlMapper.getCharge();
        vo.setChargeCoupon(chargeCoupon);
        return vo;
    }
    //订单
    //复购
    //充值
}
