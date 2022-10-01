package com.example.books.controller;

import com.example.books.bean.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
public class LoginController {
    @PostMapping("/login")
    public Msg login(String username,String password){
        if(!StringUtils.isEmpty(username)&&password.equals("123456")){
            return Msg.success().add("success","登录保持");
        }else {
            return Msg.fail("error").add("error","账号密码错误");
        }
    }
}
