package com.example.books.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.books.bean.User;
import com.example.books.mapper.UserMapper;
import com.example.books.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
