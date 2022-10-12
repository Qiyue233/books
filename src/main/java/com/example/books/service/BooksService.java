package com.example.books.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.books.bean.Books;
import com.example.books.bean.Msg;


public interface BooksService extends IService<Books> {


    public Msg getBookById(int id);

    public Msg update(String content, int in_number,double out_price,double int_price,String isbn);
    public Msg del(int id);




}
