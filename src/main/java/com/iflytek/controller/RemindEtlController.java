package com.iflytek.controller;

import com.iflytek.domain.CouponReminder;
import com.iflytek.domain.FreeReminder;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

//优惠券提醒相关
@Controller
public class RemindEtlController {
    //首单免费提醒
    public static List<FreeReminder> freeReminderList(){
        List<FreeReminder> freeReminders = new ArrayList<>();
        return freeReminders;
    }
    //失效提醒
    public static List<CouponReminder> couponReminders(){
        List<CouponReminder> couponReminders = new ArrayList<>();
        return couponReminders;
    }
}
