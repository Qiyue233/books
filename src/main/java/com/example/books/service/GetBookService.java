package com.example.books.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.books.bean.Books;
import com.example.books.bean.Msg;

public interface GetBookService extends IService<Books> {
    Msg getAllBook(int pageNum);
    Msg getType();
    Msg getAllBooksByType(int type);
    Msg getBookById( int id);
    Msg getBookByName(String book_name);
    Msg getBookByType(int type);
    Msg getBookByIsbn(String isbn);
    int countByName(String book_name);
    int countByType(int type);
    int countByIsbn(String isbn);
}
