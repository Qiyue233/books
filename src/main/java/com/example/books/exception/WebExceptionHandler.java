package com.example.books.exception;

import com.example.books.bean.Msg;
import org.apache.ibatis.binding.BindingException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: qi yue
 * @Date: 14:07 2021/12/3
 * @Description:
 * @Version v1.0
 */
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {

    @ExceptionHandler(value = WebException.class)
    public Msg webException(WebException e){
        return new Msg(303,e.getErrorMsg());
    }

    @ExceptionHandler(value =NumberFormatException.class)
    public Msg NumberFormatException(){
        return Msg.fail("请求参数类型错误");
    }

    @ExceptionHandler(value = BindingException.class)
    public Msg BindingException(){
        return Msg.fail("操作失败，不存在的数值");
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Msg HttpRequestMethodNotSupportedException(){
        return Msg.fail("不存在的接口调用");
    }


}
