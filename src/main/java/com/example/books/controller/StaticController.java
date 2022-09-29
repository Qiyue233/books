package com.example.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {
    @GetMapping("/")
    public String gotoLogin(){
        return "login";
    }
    @GetMapping("index")
    public String gotoIndex(){
        return "index";
    }

    @GetMapping("/register")
    public String gotoRegister(){
        return "register";
    }
}
