package com.example.books.service;

import com.example.books.bean.Msg;

public interface LoginService {
    Msg getKey(String user);
    Msg register(String username,String password);
    Msg record(String username,String password);
}
