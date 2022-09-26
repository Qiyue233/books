package com.example.books.controller;


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
    public Msg getAllBook(){
        return Msg.success().add("books",booksService.list());
    }
    @GetMapping("/getBooksByName")
    public Msg getBookByName(String bookName){
        return booksService.getBookByName(bookName);
    }

    @PostMapping("/putBook")
    public Msg putBook(String isbn,String cip,int type
            ,String name,String content,String author
            ,String outDate,String entireNumber
            ,double setPrice,double intPrice){

        return booksService.putBook(isbn,cip,type,name,content,
                author,outDate,entireNumber,setPrice,intPrice);
    }

}
