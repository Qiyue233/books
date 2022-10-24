package com.example.books.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.books.bean.BookLog;
import com.example.books.bean.Msg;
import com.example.books.mapper.BookLogMapper;
import com.example.books.mapper.BooksMapper;
import com.example.books.service.AddBookService;
import com.example.books.util.UIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class AddBookServiceImpl extends ServiceImpl<BookLogMapper, BookLog> implements AddBookService {

    @Resource
    BookLogMapper bookLogMapper;
    @Resource
    BooksMapper booksMapper;

    @Override
    @Transactional
    public Msg addBook(String bookName, String isbn, String cip
            , String author, double setPrice, double price
            , Date outDate, String publisher, int type,int number) {
        String uid= UIDUtil.getUID("GMTS");
        List<String> list=booksMapper.selectIsbn();
        boolean bool=false;
        for (String isbn1:list ) {
            if (isbn.equals(isbn1)){
                bool=true;
                break;
            }
        }

        if (bool){
            booksMapper.update(number,isbn);
            return Msg.success();
        }
        System.out.println("进行添加");
        booksMapper.insert(isbn,cip,type,bookName,author,publisher,outDate,setPrice,number);

        java.util.Date date = new java.util.Date();
        SimpleDateFormat queueDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String input_time = queueDateFormat.format(date);
        System.out.println(uid);
        System.out.println(input_time);
        bookLogMapper.insert(uid,isbn,input_time,setPrice,price,number);
        return Msg.success();
    }
}
