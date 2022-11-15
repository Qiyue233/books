package com.example.books.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.books.bean.Books;
import com.example.books.bean.Borrowing;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BorrowingMapper extends BaseMapper<Borrowing> {
    //借阅操作
    @Select("SELECT book_name from books where id=#{id}")
    String selectNameById(@Param("id") int id);
    @Select("select phone_number from user where user_name=#{userName}")
    String getTelNumber(@Param("userName") String userName);

    @Insert("INSERT INTO BookManagement.borrowing (bookId,bookName,userName,telNumber,out_date,estimated_date)" +
            "values(#{id},#{name},#{userName},#{telNumber},#{out_date},#{estimatedDate})")
    void borrowing(@Param("id") int id, @Param("name") String name,
                   @Param("userName") String userName,@Param("telNumber")String telNumber,
                   @Param("out_date") String out_date,@Param("estimatedDate")String estimatedDate);

    @Select("select *from borrowing where bookId=#{id}")
     String selectInfoById(@Param("id") int id);

    @Update("update books set state=#{state} where id=#{id};")
    void updateStateByBorrowId(@Param("id") int id,@Param("state")String state);

    //借阅记录
    @Select("select *from borrowing where bookName=#{bookName}")
    List<Borrowing> selectByName(@Param("bookName") String bookName);
    @Select("select *from borrowing where userName=#{userName}")
    List<Borrowing> selectByUserName(@Param("userName") String userName);
    @Select("select *from borrowing where telNumber=#{tel}")
    List<Borrowing> selectByTel(@Param("tel") String tel);
}
