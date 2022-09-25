package com.example.books.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("books")
@Data
public class Books {
    @TableField("id")
    int id;
    @TableField("isbn")
    String isbn;
    @TableField("cip")
    String cip;
    @TableField("type")
    int type;
    @TableField("book_name")
    String bookName;
    @TableField("content")
    String content;
    @TableField("author")
    String author;
    @TableField("out_date")
    Date outDate;
    @TableField("publisher")
    String publisher;
    @TableField("entire_number")
    int entireNumber;
    @TableField("in number")
    int inNumber;
    @TableField("set price")
    double setPrice;
    @TableField("int price")
    double intPrice;
    @TableField("out price")
    double outPrice;
}
