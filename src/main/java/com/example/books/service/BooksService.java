package com.example.books.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.books.bean.Books;
import com.example.books.bean.Msg;

import java.util.List;

public interface BooksService extends IService<Books> {
    public Msg getBookByName(String bookName);
    public Msg putBook(String isbn,String cip,int type
            ,String name,String content,String author
            ,String outDate,String entireNumber
            ,double setPrice,double intPrice);
}
