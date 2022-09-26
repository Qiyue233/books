package com.example.books.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.books.bean.Books;
import com.example.books.bean.Borrowing;
import com.example.books.mapper.BorrowingMapper;
import com.example.books.service.BorrowingService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BorrowingServiceImpl extends ServiceImpl<BorrowingMapper, Borrowing> implements BorrowingService  {

}
