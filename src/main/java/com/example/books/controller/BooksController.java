package com.example.books.controller;


import com.example.books.bean.Msg;
import com.example.books.service.BooksService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@ResponseBody
public class BooksController {

    @Resource
    BooksService booksService;

    @GetMapping("/getAllBooks")
    public Msg getAllBook(){
        return Msg.success().add("books",booksService.list()).add("nums",booksService.count());
    }
    @GetMapping("/getBooksByName")
    public Msg getBookByName(String bookName){
        return booksService.getBookByName(bookName);
    }

    @GetMapping("/updateById")
    public Msg updateById( int id){
        System.out.println(booksService.getBookById(id));
        return booksService.getBookById(id);
    }
    @PostMapping("/updateAjax")
    public Msg update( String content, int in_number,double out_price,double int_price,String isbn){
        System.out.println(content);
        System.out.println(in_number);
        System.out.println(out_price);
        System.out.println(int_price);
        System.out.println(isbn);
        return booksService.update(content, in_number, out_price, int_price,isbn);
    }
    @PostMapping("/putBook")
    public Msg putBook(String isbn,String cip,int type
            ,String book_name,String content,String author
            ,String out_date,int entire_number
            ,double int_price,double set_price){

        return booksService.putBook(isbn,cip,type,book_name,content,
                author,out_date,entire_number,int_price,set_price);

    }

}
