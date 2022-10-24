package com.example.books.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.books.bean.Books;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;


@Mapper
public interface BooksMapper extends BaseMapper<Books> {

    @Insert("INSERT INTO BookManagement.books (isbn, cip, type, book_name, author, out_date, publisher,in_number, set_price) " +
            "values (#{isbn},#{cip},#{type},#{book_name},#{author},#{outDate},#{publisher},#{number},#{set_price});")
    void  insert(@Param("isbn") String isbn, @Param("cip") String cip, @Param("type") int type
            , @Param("book_name") String book_name, @Param("author") String author
            , @Param("publisher") String publisher,@Param("outDate")Date outDate
            , @Param("set_price") double set_price, @Param("number") int number);
    //book_search_result.html  search类型 name、type、isbn
    @Select("select id from BookManagement.book_Type WHERE type=#{type}")
    int getTypeByType(@Param("type") String type);
    @Select("select * from BookManagement.books WHERE type=#{type}")
    List<Books> selectByType(int type);
    @Select("SELECT * FROM BookManagement.books WHERE book_name=#{book_name}")
    List<Books> selectByName(String book_name);
    @Select("SELECT * FROM BookManagement.books WHERE isbn=#{isbn}")
    List<Books> selectByIsbn(String isbn);
/*    @Select("SELECT COUNT(*) FROM BookManagement.books WHERE book_name=#{book_name}")
    int selectCountByName(String book_name);


    @Select("SELECT COUNT(*) FROM BookManagement.books WHERE type=#{type}")
    int selectCountByType(int type);

    @Select("SELECT COUNT(*) FROM BookManagement.books WHERE isbn=#{isbn};")
    int selectCountByIsbn(String isbn);*/

    @Select("select isbn from books;")
    List<String> selectIsbn();

    @Insert("update books set in_number=in_number+ #{#number} where isbn = #{#isbn};")
    void update(@Param("number")int number,@Param("isbn")String isbn);

    @Insert("update books set in_number=#{in_number} ,out_number=#{out_number} ,set_price=#{set_price} where isbn = #{isbn};")
    void updateByIsbn(@Param("in_number")int in_number,@Param("out_number")int out_number
            ,@Param("set_price")double set_price ,@Param("isbn")String isbn);

    @Delete("delete from BookManagement.books WHERE id=#{id}")
    void deleteById(@Param("id") int id);


}
