package com.example.books.controller;

import com.example.books.annotation.JwtToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {
    @GetMapping("/")
    public String gotoLogin(){
        return "login";
    }
    @GetMapping("index")
    public String gotoIndex(){
        return "index";
    }
    @GetMapping("/register")
    public String gotoRegister(){
        return "register";
    }


    @GetMapping("/book")
    public String gotoBook(){
        return "/book/book";
    }
    @GetMapping("/addbook")
    public String gotoAddBook(){
        return "/book/addbook";
    }
    /*
    @GetMapping("/putBook")
    public String putBook(){
        return "/admin/book_add";
    }
    @GetMapping("/update")
    public String update(){
        return "/admin/book_edit";
    }*/
    @GetMapping("searchBook")
    public String searchBook(){
        return "/book/book_search_result";
    }
    @GetMapping("/readers")
    public String readers(){
        return "/admin/readers";
    }
    @GetMapping("/records")
    public String records(){
        return "/book/records";
    }
    @GetMapping("/borrow")
    public String borrow(){
        return "/book/borrow";
    }
    @GetMapping("/ownRecord")
    public String ownRecord(){
        return "/user/ownRecord";
    }
}
