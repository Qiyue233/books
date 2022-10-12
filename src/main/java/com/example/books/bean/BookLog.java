package com.example.books.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("book_log")
public class BookLog {
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private int id;
    @TableField("uid")
    private int uid;
    @TableField("isbn")
    private String isbn;
    @TableField(value = "input_time",fill = FieldFill.INSERT )
    private Date inputTime;
    @TableField("set_price")
    private double setPrice;
    @TableField("price")
    private double price;
}
