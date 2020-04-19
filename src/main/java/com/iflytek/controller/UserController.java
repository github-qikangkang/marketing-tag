package com.iflytek.controller;

import com.iflytek.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    //初始化UI
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    //指标UI
    @GetMapping("/user/index")
    public String userIndex(){
        return "/user/index";
    }
    //标签UI
    @GetMapping("/user/tags")
    public String userTags(){
        return "/user/tags";
    }
    //登录UI
    @GetMapping("/login")
    public String login(){
        return "index";
    }
    //404UI
    @GetMapping("/unauthority")
    public String unauthority(){
        return "unauthority";
    }
    //登录验证

    @PostMapping("/login")
    @ResponseBody
    public String login(User user){
        System.out.println("======_______________"+user.toString());
        AuthenticationToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return "/user/index";
    }
}
