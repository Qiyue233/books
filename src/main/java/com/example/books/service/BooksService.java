package com.example.books.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.books.bean.Books;
import com.example.books.bean.Msg;



public interface BooksService extends IService<Books> {
    public Msg getBookByName(String bookName);
    public Msg putBook(String isbn,String cip,int type
            ,String name,String content,String author
            ,String out_date,int entire_number
            ,double int_price,double set_price);

    public Msg getBookById(int id);
    public Msg update(String content, int in_number,double out_price,double int_price,String isbn);
}
