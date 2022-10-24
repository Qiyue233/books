package com.example.books.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.books.bean.BookLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface BookLogMapper extends BaseMapper<BookLog> {
    @Insert("INSERT INTO BookManagement.book_log (uid, isbn, input_time, set_price, price, number)" +
            "VALUES (#{uid}, #{isbn}, #{input_time}, #{setPrice}, #{price}, #{number});")
    void insert(@Param("uid") String uid,@Param("isbn") String isbn,
                @Param("input_time") String input_time, @Param("setPrice") double setPrice,
                @Param("price") double price,@Param("number") double number);
}
