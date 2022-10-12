package com.example.books.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.books.bean.BookLog;
import com.example.books.bean.Msg;

import java.sql.Date;

public interface AddBookService extends IService<BookLog> {
    Msg addBook(String bookName, String isbn, String cip
            , String author, double setPrice, double price
            , Date outDate, String publisher, int type,int number);
}
