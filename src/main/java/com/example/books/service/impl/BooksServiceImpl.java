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
    public Msg putBook(String isbn, String cip, int type, String name, String content, String author, String outDate, String entireNumber, double setPrice, double intPrice) {
        //TODO 检校
        booksMapper.insert(isbn,cip,type,name,content,
                author,outDate,entireNumber,setPrice,intPrice);
        return Msg.success();
    }
}
