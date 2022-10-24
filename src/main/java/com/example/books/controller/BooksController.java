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
    //修改时根据id获取书籍信息
    @GetMapping("/updateById")
    @JwtToken
    public Msg updateById( int id){
        return booksService.getBookById(id);
    }
    //进行修改
    @PostMapping("/updateAjax")
    @JwtToken
    public Msg update( int inNumber,int outNumber,double setPrice,String isbn){

        return booksService.update(inNumber,outNumber , setPrice,isbn);
    }
    //根据id删除
    @GetMapping("/del")
    @JwtToken
    public Msg del(int[] id){
        return booksService.del(id);
    }

}
