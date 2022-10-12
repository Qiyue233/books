package com.example.books.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.books.bean.Books;
import com.example.books.bean.Msg;
import com.example.books.mapper.BooksMapper;
import com.example.books.service.BooksService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements BooksService {
    @Resource
    BooksMapper booksMapper;

    @Override
    public Msg getBookById(int id) {
        return Msg.success();
    }

    @Override
    public Msg update(String content, int in_number,double out_price,double int_price,String isbn) {
        return Msg.success();
    }

    @Override
    public Msg del(int id) {
        booksMapper.deleteById(id);
        return  Msg.success();
    }





}
