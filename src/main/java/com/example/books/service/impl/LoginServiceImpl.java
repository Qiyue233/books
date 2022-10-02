package com.example.books.service.impl;

import com.example.books.bean.Msg;
import com.example.books.bean.User;
import com.example.books.mapper.UserMapper;
import com.example.books.service.LoginService;
import com.example.books.util.JWTUtil;
import com.example.books.util.RSAUtil;
import com.example.books.util.RandomUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.KeyPair;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserMapper userMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public Msg getKey(String user) {
        KeyPair keyPair;
        String publicKey="";
        String privateKey="";

        try {
            keyPair=RSAUtil.getKeyPair();
            publicKey= Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
            privateKey= Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        }catch (Exception e){
        }

        if (privateKey.equals("")||publicKey.equals("")){
            return Msg.fail("未知的错误，请稍后重试");
        }
        if (redisTemplate.opsForValue().get(user)!=null){
            redisTemplate.opsForValue().getAndDelete(user);
        }
        redisTemplate.opsForValue().set(user,privateKey,10000,TimeUnit.SECONDS);
        return Msg.success().add("key",publicKey);
    }

    @Override
    public Msg register(String username, String password) {
        if (userMapper.countByName(username)>0){
            return Msg.fail("用户名重复，请重新输入！");
        }
        String privateKey= (String) redisTemplate.opsForValue().getAndDelete(username);
        try {
            password= RSAUtil.privateDecrypt(password, privateKey);
        }catch (Exception e){
        }
        String salt= RandomUtil.getRandom();
        password=DigestUtils.md5DigestAsHex((password+salt).getBytes(StandardCharsets.UTF_8));
        userMapper.insert(username,password,salt);
        return Msg.success();
    }

    @Override
    public Msg record(String username, String password) {
        User user=userMapper.selectByName(username);
        if (user==null){
            return Msg.fail("不存在的用户名！请注册");
        }
        String privateKey= (String) redisTemplate.opsForValue().getAndDelete(username);
        try {
            password= RSAUtil.privateDecrypt(password, privateKey);
        }catch (Exception e){
        }
        String salt=user.getSalt();
        password=DigestUtils.md5DigestAsHex((password+salt).getBytes(StandardCharsets.UTF_8));
        if (!password.equals(user.getPassword())){
            return Msg.fail("不正确的密码，请重新输入");
        }

        //生成token及密钥
        if (redisTemplate.opsForValue().get(username+"-tokenKey")!=null){
            redisTemplate.opsForValue().getAndDelete(username+"-tokenKey");
        }
        String R=RandomUtil.getRandom();
        redisTemplate.opsForValue().set(username+"-tokenKey",R,10,TimeUnit.MINUTES);
        String jwt= JWTUtil.getToken(username,R);

        return Msg.success().add("token",jwt);
    }
}
