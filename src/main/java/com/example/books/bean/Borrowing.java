package com.example.books.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("borrowing")
public class Borrowing {
    @TableField("id")
    int id;
    @TableField("bookId")
    int bookId;
    @TableField("bookName")
    String bookName;
    @TableField("userName")
    String userName;
    @TableField("telNumber")
    String telNumber;
    @TableField("out_date")
    Date outDate;
    @TableField("estimated_date")
    Date estimatedDate;
    @TableField("real_date")
    Date realDate;
    @TableField("deposit")
    double deposit;
    @TableField("back_deposit")
    double backDeposit;
}
