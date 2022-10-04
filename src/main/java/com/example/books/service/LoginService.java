package com.example.books.service;

import com.example.books.bean.Msg;

public interface LoginService {
    Msg getKey(String telNumber);
    Msg register(String username,String password,String telNumber);
    Msg record(String telNumber,String password);
}
