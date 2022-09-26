package com.example.books.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.books.bean.Borrowing;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BorrowingMapper extends BaseMapper<Borrowing> {
}
