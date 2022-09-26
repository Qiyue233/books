package com.example.books.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.books.bean.Books;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BooksMapper extends BaseMapper<Books> {
}
