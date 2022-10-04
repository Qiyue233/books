package com.example.books.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.books.bean.Books;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface BooksMapper extends BaseMapper<Books> {

    @Select("SELECT * from books where book_name= #{book_name} ;")
    List<Books> selectByName(@Param("book_name") String book_name);

    @Select("SELECT * from books where id= #{id} ;")
    Books selectBookById(@Param("id") int id);
    @Select("SELECT * FROM BookManagement.books WHERE type=#{type}")
    List<Books> selectByType(int type);
    @Select("SELECT * from books where isbn= #{isbn} ;")
    List<Books> selectByIsbn(String isbn);
    @Insert("INSERT INTO BookManagement.books (isbn, cip, type, book_name, content, author, out_date, set_price, int_price, state) " +
            "values (#{isbn},#{cip},#{type},#{book_name},#{content},#{author},#{out_date},#{set_price},#{int_price},#{state})")
    void  insert(@Param("isbn") String isbn,@Param("cip") String cip,@Param("type") int type
            ,@Param("book_name") String book_name,@Param("content") String content,@Param("author") String author
            ,@Param("out_date") String out_date,@Param("set_price") double set_price,@Param("int_price")double int_price
            ,@Param("state") String state);

    @Update("update BookManagement.books set  content=#{content}, in_number=#{in_number}, out_price=#{out_price}, int_price=#{int_price} where isbn=#{isbn}")
    void update(@Param("content") String content,@Param("in_number") int in_number
            ,@Param("out_price") double out_price,@Param("int_price")double int_price
            ,@Param("isbn") String isbn);

    @Select("SELECT COUNT(*) FROM BookManagement.books WHERE book_name=#{book_name}")
    int selectCountByName(String book_name);

    @Select("SELECT COUNT(*) FROM BookManagement.books WHERE type=#{type}")
    int selectCountByType(int type);

    @Select("SELECT COUNT(*) FROM BookManagement.books WHERE isbn=#{isbn}")
    int selectCountByIsbn(String isbn);
}
