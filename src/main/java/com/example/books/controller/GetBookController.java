package com.example.books.controller;

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
    public Msg getAllBook(int pageNum){
        return getBookService.getAllBook(pageNum);
    }
    //获取所有书籍类别信息
    @GetMapping("/getType")
    public Msg getType(){
        return  getBookService.getType();
    }


    @GetMapping("getAllBooksByType")
    public Msg getAllBooksByType(int type){
        return getBookService.getAllBooksByType(type);
    }

    //选中根据id获取信息
    @GetMapping("/checkById")
    public Msg getBookById( int id){
        return getBookService.getBookById(id);
    }

    //根据书名搜索
    @GetMapping("/getBooksByName")
    public Msg getBookByName(String book_name){
        return getBookService.getBookByName(book_name);
    }
    //根据类型搜索
    @GetMapping("/getBooksByType")
    public Msg getBooksByType(int type){
        return getBookService.getBookByType(type);
    }
    //根据isbn搜索
    @GetMapping("/getBooksByIsbn")
    public Msg getBooksByIsbn(String isbn){
        return getBookService.getBookByIsbn(isbn);
    }

}
