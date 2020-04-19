package com.iflytek.controller;

import com.iflytek.domain.MemberChannel;
import com.iflytek.domain.MemberHeat;
import com.iflytek.domain.MemberMpSub;
import com.iflytek.domain.MemberSex;
import com.iflytek.mapper.MemberEtlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberEtlController {
    /**
     * sex  性别指标 0未知 1 男 2女
     * */
    @Autowired
    private MemberEtlMapper memberEtlMapper;
    @RequestMapping("/memberSex")
    @ResponseBody
    public List<MemberSex> memberSex(){
        System.out.println(memberEtlMapper+"=====================");
        List<MemberSex> memberSexes = memberEtlMapper.getMemberSex();
        System.out.println(memberSexes.toString());
        return memberSexes;
    }

    //注册渠道的统计
    @RequestMapping("/memberRegChannel")
    @ResponseBody
    public List<MemberChannel> memberRegChannel(){
        List<MemberChannel> memberChannels = memberEtlMapper.getMemberRegChannel();
        return memberChannels;
    }
    //是否关注微信公众号
    @RequestMapping("/memberMpSub")
    @ResponseBody
    public List<MemberMpSub> memberMpSub(){
        List<MemberMpSub> memberMpSubs = memberEtlMapper.getMemberMpSub();
        return  memberMpSubs;
    }
    //热度统计
    @RequestMapping("/memberHeat")
    @ResponseBody
    public MemberHeat memberHeat(){
        MemberHeat memberHeat = memberEtlMapper.getMemberHeat();
        Integer coupon = memberEtlMapper.getCoupon();
        Integer order = memberEtlMapper.getOrder();
        Integer orderAgain = memberEtlMapper.getOrderAgain();
        memberHeat.setCoupon(coupon);
        memberHeat.setOrder(order);
        memberHeat.setOrderAgain(orderAgain);
        return memberHeat;
    }
}
