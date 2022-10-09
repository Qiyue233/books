package com.example.books.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("book_log")
public class bookLog {
    @TableField("id")
    private int id;
    @TableField("isbn")
    private String isbn;
    @TableField("name")
    private String name;
    @TableField("input_time")
    private Time inputTime;
    @TableField("price")
    private double price;
}
