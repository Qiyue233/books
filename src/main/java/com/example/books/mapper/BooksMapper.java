package com.example.books.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.books.bean.Books;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BooksMapper extends BaseMapper<Books> {

    @Select("SELECT * from books where book_name= #{name} ;")
    List<Books> selectByName(@Param("name") String name);

    @Insert("insert into BookManagement.books(id, isbn, cip, type, book_name, content, author, out_date, publisher, entire_number, in_number, set_price, int_price, out_price) " +
            "values (#{isbn},#{cip},#{type},#{name},#{content},#{author},#{outDate},#{entireNumber},#{setPrice},#{intPrice});")
    void  insert(@Param("isbn") String isbn,@Param("cip") String cip,@Param("type") int type
            ,@Param("name") String name,@Param("content") String content,@Param("author") String author
            ,@Param("outDate") String outDate,@Param("entireNumber") String entireNumber
            ,@Param("setPrice") double setPrice,@Param("intPrice") double intPrice);

}
