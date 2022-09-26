package com.example.books.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.books.bean.FeedBack;
import com.example.books.mapper.FeedBackMapper;
import com.example.books.service.FeedBackService;
import org.springframework.stereotype.Service;

@Service

public class FeedBackServiceImpl extends ServiceImpl<FeedBackMapper,FeedBack> implements FeedBackService {
}
