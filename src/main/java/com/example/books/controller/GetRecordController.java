package com.example.books.controller;

import com.example.books.bean.Books;
import com.example.books.bean.Borrowing;
import com.example.books.bean.Msg;
import com.example.books.service.GetBookService;
import com.example.books.service.GetRecodService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@ResponseBody
public class GetRecordController {

    @Resource
    GetRecodService getRecodService;

    @GetMapping("/searchRecordByName")
    public Msg searchRecordByName(int pageNum,String bookName){
        return getRecodService.getBookByName(pageNum,bookName);
    }
    @GetMapping("/searchRecordByUserName")
    public Msg searchRecordByUserName(int pageNum,String userName){
        return getRecodService.getBookByUserName(pageNum,userName);
    }
    @GetMapping("/searchBooksByTel")
    public Msg searchBooksByTel(int pageNum,String tel){
        return getRecodService.getBookByTel(pageNum,tel);
    }
}
