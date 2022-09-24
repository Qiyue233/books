package com.example.books.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Msg {

    //状态码
    private int code;

    //提示信息
    private String msg;

    //用户返回给浏览器的信息
    private Map<String,Object> extend= new HashMap<>();

    //成功返回
    public static Msg success(){
        Msg result=new Msg();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    //失败返回
    public static Msg fail(String msg){
        Msg result=new Msg();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

    //构建消息
    public Msg add(String key,Object value){
        this.getExtend().put(key,value);
        return this;
    }
}
