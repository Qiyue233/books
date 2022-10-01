package com.example.books.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("borrowing")
public class Borrowing {
    @TableField("id")
    int id;
    @TableField("bookId")
    int bookId;
    @TableField("back")
    boolean back;
    @TableField("out_time")
    Data outTime;
    @TableField("estimated_time")
    Data estimatedTime;
    @TableField("real_time")
    Data realTime;
    @TableField("deposit")
    double deposit;
    @TableField("back_deposit")
    double backDeposit;
}
