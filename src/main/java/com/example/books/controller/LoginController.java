package com.example.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session){
        if(!StringUtils.isEmpty(username)&&password.equals("123456")){
            session.setAttribute("LoginUserName",username);
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","账号或密码错误");
            return "login";
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
