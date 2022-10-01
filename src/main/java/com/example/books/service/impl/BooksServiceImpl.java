package com.example.books.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.books.bean.Books;
import com.example.books.bean.Msg;
import com.example.books.mapper.BooksMapper;
import com.example.books.service.BooksService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements BooksService {
    @Resource
    BooksMapper booksMapper;


    @Override
    public Msg getBookByName(String BookName) {
        return Msg.success().add("books",booksMapper.selectByName(BookName));
    }

    @Override
    public Msg putBook(String isbn, String cip, int type, String book_name, String content, String author, String out_date, String entire_number, double int_price, double set_price) {
        //TODO 检校

        booksMapper.insert(isbn,cip,type,book_name,content,
                author,out_date,entire_number,set_price,int_price);
        return Msg.success();
    }



}
