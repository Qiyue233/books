package com.example.books.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("feedback")
public class FeedBack {
    @TableField("id")
    int id;
    @TableField("current_time")
    Data current_time;
    @TableField("type")
    int type;
    @TableField("suggest")
    String suggest;
}
