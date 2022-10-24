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
    public Msg update(int in_number,int out_number,double set_price,String isbn) {
        booksMapper.updateByIsbn(in_number,out_number,set_price,isbn);
        return Msg.success();
    }



    @Override
    public Msg del(int[] id) {
        for (int i=0;i<id.length;i++)
        {
            booksMapper.deleteById(id[i]);
            System.out.println(id[i]);
        }
        return  Msg.success();
    }





}
