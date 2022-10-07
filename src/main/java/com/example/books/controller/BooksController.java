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
        return booksService.getBookByName(book_name).add("nums",booksService.countByName(book_name));
    }
    //根据类型搜索
     @GetMapping("/getBooksByType")
     public Msg getBooksByType(int type){
         return booksService.getBookByType(type).add("nums",booksService.countByType(type));
    }
    //根据isbn搜索
     @GetMapping("/getBooksByIsbn")
     public Msg getBooksByIsbn(String isbn){
        return booksService.getBookByIsbn(isbn).add("nums",booksService.countByIsbn(isbn));
    }
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
