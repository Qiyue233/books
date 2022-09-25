package com.example.books.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    @TableField("id")
    int id;
    @TableField("user_name")
    String userName;
    @TableField("password")
    String password;
    @TableField("key")
    String key;
    @TableField("identityName")
    String identityName;
    @TableField("identityCard")
    String identityCard;
    @TableField("phone_number")
    String phoneNumber;
    @TableField("power_a")
    int powerA;
    @TableField("power_b")
    int powerB;
    @TableField("power_c")
    int powerC;
}
