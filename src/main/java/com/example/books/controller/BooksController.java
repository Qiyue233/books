package com.example.books.controller;


import com.example.books.annotation.JwtToken;
import com.example.books.bean.BookType;
import com.example.books.bean.Books;
import com.example.books.bean.Msg;
import com.example.books.mapper.BooksMapper;
import com.example.books.service.BookTypeService;
import com.example.books.service.BooksService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@ResponseBody
//@JwtToken
public class BooksController {

    @Resource
    BooksService booksService;
    //添加书籍
    @PostMapping("/putBook")
    public Msg putBook(String isbn,String cip,int type,String book_name,
                       String author,double set_price,double int_price,
                       String state ){

        return booksService.putBook(isbn,cip,type,book_name,
                author,set_price,int_price,state);

    }
    //修改时根据id获取书籍信息
    @GetMapping("/updateById")
    public Msg updateById( int id){
        return booksService.getBookById(id);
    }
    //进行修改
    @PostMapping("/updateAjax")
    public Msg update( String content, int in_number,double out_price,double int_price,String isbn){
        return booksService.update(content, in_number, out_price, int_price,isbn);
    }
    //根据id删除
    @GetMapping("/del")
    public Msg del(int id){
        return booksService.del(id);
    }

}
