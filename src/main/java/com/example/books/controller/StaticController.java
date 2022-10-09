package com.example.books.controller;

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

    @GetMapping("/books")
    public String books(){
        return "/admin/books";
    }
    @GetMapping("/book")
    public String gotoBook(){
        return "/html/book";
    }
    @GetMapping("/addbook")
    public String gotoAddBook(){
        return "/html/addbook";
    }
    @GetMapping("/putBook")
    public String putBook(){
        return "/admin/book_add";
    }
    @GetMapping("/update")
    public String update(){
        return "/admin/book_edit";
    }
    @GetMapping("searchBook")
    public String searchBook(){
        return "/admin/book_search_result";
    }
    @GetMapping("/readers")
    public String readers(){
        return "/admin/readers";
    }
    @GetMapping("/records")
    public String records(){
        return "/admin/records";
    }
    @GetMapping("/ownRecord")
    public String ownRecord(){
        return "/user/ownRecord";
    }
}
