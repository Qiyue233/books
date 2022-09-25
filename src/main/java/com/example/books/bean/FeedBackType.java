package com.example.books.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("feedBackType")
public class FeedBackType {
    @TableField("id")
    int id;
    @TableField("type")
    String type;
    @TableField("illustrate")
    String illustrate;
}
