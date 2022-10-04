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
    @Select("SELECT count(*) from user where user_name= #{user} ;")
    public int countByName(@Param("user")String user);
    @Select("SELECT count(*) from user where phone_number= #{telNumber} ;")
    public int countByTel(@Param("telNumber")String telNumber);
    @Select("SELECT * from user where user_name= #{user} ;")
    public User selectByName(@Param("user")String user);
    @Select("SELECT * from user where phone_number= #{telNumber} ;")
    public User selectByTel(@Param("telNumber")String telNumber);
    @Insert("INSERT INTO BookManagement.user (user_name, password, salt, phone_number)" +
            "VALUES (#{userName}, #{password}, #{salt}, #{telNumber});")
    public void insert(@Param("userName")String userName,@Param("password")String password,
                       @Param("salt")String salt,@Param("telNumber")String telNumber);
}
