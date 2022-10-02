package com.example.books.controller;


import com.example.books.annotation.JwtToken;
import com.example.books.bean.Msg;
import com.example.books.service.BooksService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@ResponseBody

public class BooksController {

    @Resource
    BooksService booksService;

    @GetMapping("/getAllBooks")
    @JwtToken
    public Msg getAllBook(){
        return Msg.success().add("books",booksService.list()).add("nums",booksService.count());
    }
    @GetMapping("/getBooksByName")
    public Msg getBookByName(String bookName){
        return booksService.getBookByName(bookName);
    }

    @PostMapping("/putBook")
    public Msg putBook(String isbn,String cip,int type
            ,String book_name,String content,String author
            ,String out_date,String entire_number
            ,double int_price,double set_price){

        return booksService.putBook(isbn,cip,type,book_name,content,
                author,out_date,entire_number,int_price,set_price);

    }

}
