package com.example.books.service;

import com.example.books.bean.Borrowing;
import com.example.books.bean.Msg;

import java.util.List;

public interface GetRecodService {

    Msg getBookByName(int pageNum, String bookName);

    Msg getBookByUserName(int pageNum, String userName);

    Msg getBookByTel(int pageNum, String tel);
}
