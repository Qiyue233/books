package com.example.books.controller;

import com.example.books.annotation.JwtToken;
import com.example.books.bean.Msg;
import com.example.books.service.GetBookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@ResponseBody

public class GetBookController {
    @Resource
    GetBookService getBookService;

    @GetMapping("/getAllBooks")
    @JwtToken
    public Msg getAllBook(int pageNum){
        return getBookService.getAllBook(pageNum);
    }
    //获取所有书籍类别信息
    @GetMapping("/getType")
    @JwtToken
    public Msg getType(){
        return  getBookService.getType();
    }


    //选中根据id获取信息
    @GetMapping("/checkById")
    @JwtToken
    public Msg getBookById( int id){
        return getBookService.getBookById(id);
    }

    //根据书名搜索
    @GetMapping("/getBooksByName")
    @JwtToken
    public Msg getBookByName(Integer pageNum,String book_name){
        return getBookService.getBookByName(pageNum,book_name);
    }
    //把类型转换成对应的数字
    @GetMapping("/getTypeByType")
    @JwtToken
    public Msg getTypeByType(String type){
        return getBookService.getTypeByType(type);
    }
    //根据类型搜索
    @GetMapping("/getBooksByType")
    @JwtToken
    public Msg getBooksByType(Integer pageNum,Integer type){
        return  getBookService.getBookByType(pageNum,type);
    }

    //根据isbn搜索
    @GetMapping("/getBooksByIsbn")
    @JwtToken
    public Msg getBooksByIsbn(Integer pageNum,String isbn){
        return getBookService.getBookByIsbn(pageNum,isbn);
    }

}
