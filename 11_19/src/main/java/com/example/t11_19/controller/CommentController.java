package com.example.t11_19.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommentController {

    @GetMapping("/articles/{id}")
    public String readArticle() {
        return "view";
    }
}
