package com.example.books.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.books.bean.FeedBackType;
import com.example.books.mapper.FeedBackTypeMapper;
import com.example.books.service.FeedBackService;
import com.example.books.service.FeedBackTypeService;
import org.springframework.stereotype.Service;

@Service
public class FeedBackTypeServiceImpl extends ServiceImpl<FeedBackTypeMapper, FeedBackType> implements FeedBackTypeService {
}
