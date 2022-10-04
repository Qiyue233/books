package com.example.books.controller;

import com.example.books.bean.Msg;
import com.example.books.service.LoginService;
import com.example.books.util.FINAL;
import com.example.books.util.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
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
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    LoginService loginService;
    @GetMapping("/key")
    public Msg Key(String telNumber){
        if (!telNumber.matches(FINAL.TELPAT)){
            return Msg.fail("用户名格式错误");
        }
        return loginService.getKey(telNumber);
    }
    @GetMapping("/record")
    public Msg record(String telNumber,String password){
        String privateKey= (String) redisTemplate.opsForValue().getAndDelete(telNumber);
        try {
            password= RSAUtil.privateDecrypt(password, privateKey);
        }catch (Exception e){}
        if (!telNumber.matches(FINAL.TELPAT)){
            return Msg.fail("用户名格式错误");
        }else if (!password.matches(FINAL.PASSPAT)){
            return Msg.fail("密码格式错误");
        }
        return loginService.record(telNumber,password);
    }

    @PostMapping("/register")
    public Msg register(String username,String password,String telNumber){
        String privateKey= (String) redisTemplate.opsForValue().getAndDelete(telNumber);
        try {
            password= RSAUtil.privateDecrypt(password, privateKey);
        }catch (Exception e){}
        if (!telNumber.matches(FINAL.TELPAT)){
            return Msg.fail("用户名格式错误");
        }else if (!password.matches(FINAL.PASSPAT)){
            System.out.println("p:"+password);
            return Msg.fail("密码格式错误");
        }else if (!username.matches(FINAL.USERPAT)){
            return Msg.fail("用户名格式错误");
        }
        return loginService.register(username,password,telNumber);
    }
}
