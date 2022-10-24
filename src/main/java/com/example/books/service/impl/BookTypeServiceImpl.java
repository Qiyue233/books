package com.example.books.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.books.bean.BookType;
import com.example.books.bean.Msg;
import com.example.books.mapper.BookTypeMapper;
import com.example.books.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookTypeServiceImpl extends ServiceImpl<BookTypeMapper, BookType> implements BookTypeService {
   @Resource
   BookTypeMapper bookTypeMapper;

    @Override
    public Msg getType() {
        return Msg.success().add("type", bookTypeMapper.getType());
    }

  /*  @Override
    public Msg getAllBooksByType(int type) {
        return Msg.success().add("type", bookTypeMapper.getAllBooksByType(type));
    }*/

    @Override
    public Msg getTypeById(int id) {
        System.out.println(bookTypeMapper.getTypeById(id));
        return Msg.success().add("bookType",bookTypeMapper.getTypeById(id));
    }
}

