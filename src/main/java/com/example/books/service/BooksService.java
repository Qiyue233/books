package com.example.books.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.books.bean.Books;
import com.example.books.bean.Msg;



public interface BooksService extends IService<Books> {

    public Msg putBook(String isbn,String cip,int type
            ,String name,String content,String author
            ,String out_date,double int_price,double set_price
            ,String state );

    public Msg getBookById(int id);
    public Msg getBookByName(String book_name);
    public Msg getBookByType(int type);
    public Msg getBookByIsbn(String isbn);
    public Msg update(String content, int in_number,double out_price,double int_price,String isbn);
    public Msg del(int id);

    public int countByName(String book_name);
    public int countByType(int type);
    public int countByIsbn(String isbn);
}
