package com.example.books.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.books.bean.BookType;
import com.example.books.bean.Msg;


public interface BookTypeService extends IService<BookType> {
    public Msg getType();

/*    Msg getAllBooksByType(int type);*/

    Msg getTypeById(int id);
}
