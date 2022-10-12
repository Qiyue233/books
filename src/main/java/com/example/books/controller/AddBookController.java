package com.example.books.controller;

import com.example.books.bean.Msg;
import com.example.books.service.AddBookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Date;

@Controller
@ResponseBody
public class AddBookController {
    @Resource
    AddBookService addBookService;

    @GetMapping("/addBook")
    public Msg addBook(String bookName, String isbn, String cip
            , String author, Date outDate, String publisher
            , int type, double setPrice, double price,int number){
        //bookName,isbn,cip,author,setPrice,price,number用于日志
        //bookName,isbn,cip,author,setPrice,outDate,publisher,type,number用于入库
        return addBookService.addBook(bookName,isbn,cip,author,setPrice,price,outDate,publisher,type,number);
    }
}
