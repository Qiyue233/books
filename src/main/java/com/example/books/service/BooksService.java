package com.example.books.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.books.bean.Books;
import com.example.books.bean.Msg;


public interface BooksService extends IService<Books> {


    public Msg getBookById(int id);

    public Msg update(int inNumber,int outNumber,double setPrice,String isbn );
    public Msg del(int[] id);




}
