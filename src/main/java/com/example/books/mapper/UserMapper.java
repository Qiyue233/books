package com.example.books.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.books.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT count(1) from user where user_name= #{user} ;")
    public int countByName(@Param("user")String user);

    @Select("SELECT * from user where user_name= #{user} ;")
    public User selectByName(@Param("user")String user);

    @Insert("INSERT INTO BookManagement.user (user_name, password, salt)" +
            "VALUES (#{userName}, #{password}, #{salt});")
    public void insert(@Param("userName")String userName,@Param("password")String password,
                       @Param("salt")String salt);
}
