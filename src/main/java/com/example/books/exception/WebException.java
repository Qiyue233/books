package com.example.books.exception;

import lombok.Data;

/**
 * @Author: qi yue
 * @Date: 20:38 2021/12/3
 * @Description:
 * @Version v1.0
 */

@Data
public class WebException extends RuntimeException{

    private String errorMsg;

    public WebException(String errorMsg){
        this.errorMsg=errorMsg;
    }
}
