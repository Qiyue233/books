package com.example.books.controller;

import com.example.books.bean.Msg;
import com.example.books.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
public class LoginController {

    @Resource
    LoginService loginService;
    @GetMapping("/key")
    public Msg Key(String user){
        return loginService.getKey(user);
    }
    @GetMapping("/record")
    public Msg record(String username,String password){
        return loginService.record(username,password);
    }
    @PostMapping("/register")
    public Msg register(String username,String password){
        return loginService.register(username,password);
    }
}
