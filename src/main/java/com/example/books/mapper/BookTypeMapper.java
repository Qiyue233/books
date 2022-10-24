package com.example.books.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.books.bean.BookType;
import com.example.books.bean.Books;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookTypeMapper extends BaseMapper<BookType> {

    @Select("SELECT * FROM BookManagement.book_Type")
    List<BookType> getType();

   /* @Select("SELECT  *  FROM BookManagement.books where type=#{type}")
    List<Books> getAllBooksByType(@Param("type")  int type);
*/
    @Select("SELECT * FROM BookManagement.book_Type where id=#{id}")
    BookType getTypeById(@Param("id")  int id);
}
