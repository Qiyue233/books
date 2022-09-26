package com.example.books.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.books.bean.BookType;
import com.example.books.mapper.BookTypeMapper;
import com.example.books.service.BookTypeService;
import org.springframework.stereotype.Service;

@Service
public class BookTypeServiceImpl extends ServiceImpl<BookTypeMapper, BookType> implements BookTypeService {
}
