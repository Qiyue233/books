package com.example.books.controller;


import com.example.books.annotation.JwtToken;
import com.example.books.bean.BookType;
import com.example.books.bean.Books;
import com.example.books.bean.Msg;
import com.example.books.mapper.BooksMapper;
import com.example.books.service.BookTypeService;
import com.example.books.service.BooksService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@ResponseBody
//@JwtToken
public class BooksController {

    @Resource
    BooksService booksService;
    @Resource
    BookTypeService bookTypeService;
    //获取全部信息
    @GetMapping("/getAllBooks")
    public Msg getAllBook(int pageNum){
        PageHelper.startPage(pageNum,6);
        List<Books> list= booksService.list();
        //将查询到的数据封装到PageInfo
        PageInfo<Books> pageInfo=new PageInfo<>(list);
        return Msg.success().add("books",pageInfo).add("type",bookTypeService.list());
    }

    //获取所有书籍类别信息
    @GetMapping("/getType")
    public Msg getType(){
        return  bookTypeService.getType().add("nums",bookTypeService.count());
    }
    //根据类别获取 对应类别的所有书籍信息
    @GetMapping("getAllBooksByType")
    public Msg getAllBooksByType(int type){
        return Msg.success().add("books",bookTypeService.getAllBooksByType(type)).add("type",bookTypeService.list()).add("nums",booksService.countByType(type));
    }

    //选中根据id获取信息
    @GetMapping("/checkById")
    public Msg checkById( int id){
        return booksService.getBookById(id);
    }

    //根据书名搜索
    @GetMapping("/getBooksByName")
    public Msg getBookByName(String book_name){
        return booksService.getBookByName(book_name).add("nums",booksService.countByName(book_name));
    }
    //根据类型搜索
     @GetMapping("/getBooksByType")
     public Msg getBooksByType(int type){
         return booksService.getBookByType(type).add("nums",booksService.countByType(type));
    }
    //根据isbn搜索
     @GetMapping("/getBooksByIsbn")
     public Msg getBooksByIsbn(String isbn){
        return booksService.getBookByIsbn(isbn).add("nums",booksService.countByIsbn(isbn));
    }

    //添加书籍
    @PostMapping("/putBook")
    public Msg putBook(String isbn,String cip,int type,String book_name,
                       String author,double set_price,double int_price,
                       String state ){

        return booksService.putBook(isbn,cip,type,book_name,
                author,set_price,int_price,state);

    }
    //修改时根据id获取书籍信息
    @GetMapping("/updateById")
    public Msg updateById( int id){
        return booksService.getBookById(id);
    }
    //进行修改
    @PostMapping("/updateAjax")
    public Msg update( String content, int in_number,double out_price,double int_price,String isbn){
        return booksService.update(content, in_number, out_price, int_price,isbn);
    }
    //根据id删除
    @GetMapping("/del")
    public Msg del(int id){
        return booksService.del(id);
    }

}
