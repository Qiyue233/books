package com.example.books.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.books.bean.Books;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BooksMapper extends BaseMapper<Books> {

    @Select("SELECT * from books where book_name= #{name} ;")
    List<Books> selectByName(@Param("name") String name);

    @Select("SELECT * from books where id= #{id} ;")
    Books selectBookById(@Param("id") int id);
    @Insert("INSERT INTO BookManagement.books (isbn, cip, type, book_name, content, author, out_date, entire_number, set_price, int_price)" +
            "values (#{isbn},#{cip},#{type}," +
            "#{book_name},#{content},#{author}," +
            "#{out_date},#{entire_number}," +
            "#{set_price},#{int_price});")
    void  insert(@Param("isbn") String isbn,@Param("cip") String cip,@Param("type") int type
            ,@Param("book_name") String book_name,@Param("content") String content,@Param("author") String author
            ,@Param("out_date") String out_date,@Param("entire_number") int entire_number
            ,@Param("set_price") double set_price,@Param("int_price")double int_price);

    @Update("update BookManagement.books set  content=#{content}, in_number=#{in_number}, out_price=#{out_price}, int_price=#{int_price} where isbn=#{isbn}")
    void update(@Param("content") String content,@Param("in_number") int in_number
            ,@Param("out_price") double out_price,@Param("int_price")double int_price
            ,@Param("isbn") String isbn);
}
