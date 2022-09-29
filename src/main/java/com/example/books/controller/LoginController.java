package com.example.books.controller;

import com.example.books.bean.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
    @PostMapping("toRegister")
    public String toRegister(){
         return "redirect:/register.html";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("telcode")  String phoneNumber,
                           Model model, HttpSession session){
        if(!StringUtils.isEmpty(username)&&!password.isEmpty()&&!phoneNumber.isEmpty()) {
            return "redirect:/login.html";
        }
        else {
            model.addAttribute("msg","请将信息填写完整");
            return "register";
        }
    }
}
