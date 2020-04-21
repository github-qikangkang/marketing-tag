package com.iflytek.controller;

import com.iflytek.domain.Order;
import com.iflytek.domain.Reg;
import com.iflytek.mapper.WowEtlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

//环比指标
@Controller
public class WowEtlController {
    @Autowired
    private WowEtlMapper wowEtlMapper;
    //周注册量
    @RequestMapping("/regCountNum")
    @ResponseBody
    public List<Reg> regCountNum(){
        List<Reg> regs = wowEtlMapper.regCountNum();
        return  regs;
    }

    //周下单量
    @RequestMapping("/orderCount")
    @ResponseBody
    public List<Order> orderCount(){
        List<Order> orders = wowEtlMapper.getOrderCount();
        return orders;
    }
}
