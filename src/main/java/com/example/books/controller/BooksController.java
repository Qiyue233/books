package com.example.books.controller;


import com.example.books.annotation.JwtToken;
import com.example.books.bean.Msg;
import com.example.books.service.BooksService;
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

    //获取全部信息
    @GetMapping("/getAllBooks")
    public Msg getAllBook(){
        return Msg.success().add("books",booksService.list()).add("nums",booksService.count());
    }
    //根据书名搜索
    @GetMapping("/getBooksByName")
    public Msg getBookByName(String book_name){
        System.out.println(booksService.getBookByName(book_name).add("nums",booksService.countByName(book_name)));
        return booksService.getBookByName(book_name).add("nums",booksService.countByName(book_name));
    }
    //添加书籍
    @PostMapping("/putBook")
    public Msg putBook(String isbn,String cip,int type
            ,String book_name,String content,String author
            ,String out_date,int entire_number
            ,double int_price,double set_price){

        return booksService.putBook(isbn,cip,type,book_name,content,
                author,out_date,entire_number,int_price,set_price);

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
