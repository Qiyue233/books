package com.example.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.books.bean.Books;
import com.example.books.bean.Msg;
import com.example.books.mapper.BooksMapper;
import com.example.books.service.BooksService;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import javax.annotation.Resource;
import java.awt.print.Book;
import java.util.List;

@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements BooksService {
    @Resource
    BooksMapper booksMapper;


    @Override
    public Msg getBookByName(String book_name) {
        return Msg.success().add("books",booksMapper.selectByName(book_name));
    }

    @Override
    public Msg putBook(String isbn, String cip, int type, String book_name, String content, String author, String out_date, int entire_number, double int_price, double set_price) {
        //TODO 检校

        booksMapper.insert(isbn,cip,type,book_name,content,
                author,out_date,entire_number,set_price,int_price);
        return Msg.success();
    }

    @Override
    public Msg getBookById(int id) {
      /*  System.out.println("------------->"+id);*/
        return Msg.success().add("books",booksMapper.selectById(id));
    }

    @Override
    public Msg update(String content, int in_number,double out_price,double int_price,String isbn) {
        booksMapper.update(content, in_number, out_price, int_price,isbn);
        return Msg.success();
    }

    @Override
    public Msg del(int id) {
        booksMapper.deleteById(id);
        return  Msg.success();
    }

    @Override
    public int countByName(String book_name) {
        return  booksMapper.selectCountByName(book_name);
    }


}
