package com.example.books.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.books.bean.Books;
import com.example.books.bean.Msg;
import com.example.books.mapper.BooksMapper;
import com.example.books.service.BookTypeService;
import com.example.books.service.GetBookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GetBookServiceImpl extends ServiceImpl<BooksMapper, Books> implements GetBookService {
    @Resource
    BookTypeService bookTypeService;
    @Resource
    BooksMapper booksMapper;


    /**
     * @param pageNum
     * @return Msg
     * */
    @Override
    public Msg getAllBook(int pageNum) {
        PageHelper.startPage(pageNum,6);
        List<Books> list= this.list();
        //将查询到的数据封装到PageInfo
        PageInfo<Books> pageInfo=new PageInfo<>(list);
        return Msg.success().add("books",pageInfo).add("type",bookTypeService.list());
    }

    @Override
    public Msg getType() {
        return  bookTypeService.getType().add("nums",bookTypeService.count());
    }

    @Override
    public Msg getAllBooksByType(int type) {
        return Msg.success().add("books",bookTypeService.getAllBooksByType(type))
                .add("type",bookTypeService.list())
                .add("nums",this.countByType(type));
    }

    @Override
    public Msg getBookById(int id) {
        return Msg.success().add("books",booksMapper.selectById(id));
    }

    @Override
    public Msg getBookByName(String book_name) {
        return Msg.success().add("nums",this.countByName(book_name));
    }

    @Override
    public Msg getBookByType(int type) {
        return Msg.success().add("nums",this.countByType(type));
    }

    @Override
    public Msg getBookByIsbn(String isbn) {
        return Msg.success().add("nums",this.countByIsbn(isbn));
    }

    @Override
    public int countByName(String book_name) {
        return booksMapper.selectCountByName(book_name);
    }

    @Override
    public int countByType(int type) {
        return booksMapper.selectCountByType(type);
    }

    @Override
    public int countByIsbn(String isbn) {
        return booksMapper.selectCountByIsbn(isbn);
    }

}
