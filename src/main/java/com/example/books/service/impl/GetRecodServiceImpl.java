package com.example.books.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.books.bean.Books;
import com.example.books.bean.Borrowing;
import com.example.books.bean.Msg;
import com.example.books.mapper.BooksMapper;

import com.example.books.mapper.BorrowingMapper;
import com.example.books.service.GetRecodService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GetRecodServiceImpl extends ServiceImpl<BooksMapper, Books> implements GetRecodService {
    @Resource
    BorrowingMapper borrowingMapper;

    @Override
    public Msg getBookByName(int pageNum,String bookName) {
        PageHelper.startPage(pageNum,6);
        List<Borrowing> list= borrowingMapper.selectByName(bookName);
        //将查询到的数据封装到PageInfo
        PageInfo<Borrowing> pageInfo=new PageInfo<>(list);
        return Msg.success().add("record",pageInfo);
    }

    @Override
    public Msg getBookByUserName(int pageNum, String userName) {
        PageHelper.startPage(pageNum,6);
        List<Borrowing> list= borrowingMapper.selectByUserName(userName);
        //将查询到的数据封装到PageInfo
        PageInfo<Borrowing> pageInfo=new PageInfo<>(list);
        return Msg.success().add("record",pageInfo);
    }

    @Override
    public Msg getBookByTel(int pageNum, String tel) {
        PageHelper.startPage(pageNum,6);
        List<Borrowing> list= borrowingMapper.selectByTel(tel);
        //将查询到的数据封装到PageInfo
        PageInfo<Borrowing> pageInfo=new PageInfo<>(list);
        return Msg.success().add("record",pageInfo);
    }


}
