package com.example.books.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("books")

public class Books {
    @TableField("id")
    @TableId(type = IdType.AUTO)
    int id;
    @TableField("isbn")
    String isbn;
    @TableField("cip")
    String cip;
    @TableField("type")
    int type;
    @TableField("book_name")
    String bookName;
    @TableField("author")
    String author;
    @TableField("out_date")
    Date outDate;
    @TableField("publisher")
    String publisher;
    @TableField("out_number")
    int outNumber;
    @TableField("in_number")
    int inNumber;
    @TableField("set_price")
    double setPrice;
    @TableField("state")
    String state;
}
