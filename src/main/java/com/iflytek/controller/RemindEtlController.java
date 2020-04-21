package com.iflytek.controller;

import com.iflytek.domain.CouponReminder;
import com.iflytek.domain.FreeReminder;
import com.iflytek.mapper.RemindEtlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

//优惠券提醒相关
@Controller
public class RemindEtlController {
    @Autowired
    private RemindEtlMapper remindEtlMapper;
    //首单免费提醒
    @RequestMapping("/freeReminderList")
    @ResponseBody
    public List<FreeReminder> freeReminderList(){
        List<FreeReminder> freeReminders = remindEtlMapper.getFreeReminderList();
        return freeReminders;
    }
    //失效提醒
    @RequestMapping("/couponReminders")
    @ResponseBody
    public List<CouponReminder> couponReminders(){
        List<CouponReminder> couponReminders = remindEtlMapper.getCouponReminders();
        return couponReminders;
    }
}
