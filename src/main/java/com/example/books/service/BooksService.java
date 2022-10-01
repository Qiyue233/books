package com.example.books.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.books.bean.Books;
import com.example.books.bean.Msg;


public interface BooksService extends IService<Books> {
    public Msg getBookByName(String bookName);
    public Msg putBook(String isbn,String cip,int type
            ,String name,String content,String author
            ,String out_date,String entire_number
            ,double int_price,double set_price);
}
