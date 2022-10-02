package com.example.books.util;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public class Redis {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    public RedisTemplate<String, Object> getRedis(){
        return redisTemplate;
    }
}
