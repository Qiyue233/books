package com.example.books.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.books.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
