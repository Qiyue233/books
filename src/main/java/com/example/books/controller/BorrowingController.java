package com.example.books.controller;

import com.example.books.annotation.JwtToken;
import com.example.books.bean.Msg;
import com.example.books.service.BorrowingService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@ResponseBody
public class BorrowingController {
    @Resource
    BorrowingService borrowingService;

    //判断选中书籍是否外接
    @JwtToken
    @GetMapping("selectBorrowInfo")
    public Msg selectBorrowInfo(int[] id){
        return borrowingService.selectById(id);
    }
    //对外接书籍状态进行更改
    @JwtToken
    @GetMapping("updateStateByBorrowId")
    public  Msg updateStateByBorrowId(int[] id,String state){
        return borrowingService.updateStateByBorrowId(id,state);
    }
    //借出操作
    @JwtToken
    @Transactional(rollbackFor = {Exception.class})
    @GetMapping("borrowById")
    public Msg borrowById(int[] id,String userName){
        String state="外借";
        try{
            updateStateByBorrowId(id,state);
            borrowingService.borrowById(id,userName);
        }catch (Exception e){
        }
        return Msg.success();
    }

    //获取全部借阅记录
    @JwtToken
    @GetMapping("/getAllBorrowRecords")
    public Msg getAllBorrowRecords(int pageNum){
        return borrowingService.getAllRecords(pageNum);
    }


}
