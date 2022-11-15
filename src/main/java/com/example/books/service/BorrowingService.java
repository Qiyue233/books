package com.example.books.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.books.bean.Books;
import com.example.books.bean.Borrowing;
import com.example.books.bean.Msg;


public interface BorrowingService extends IService<Borrowing> {
    Msg borrowById(int[] id,String userName);
    String getBookNameById(int id);
    String getTelNumber(String userName);
    Msg selectById(int[] id);

    Msg updateStateByBorrowId(int[] id,String state);

    //获取借阅记录
    Msg getAllRecords(int pageNum);

}
