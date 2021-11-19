package com.example.t11_19.controller;

import com.example.t11_19.domain.Article;
import com.example.t11_19.dto.ArticleRequestDto;
import com.example.t11_19.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/articles")
    public @ResponseBody Article createArticle(@RequestBody ArticleRequestDto requestDto){
        Article article = articleService.createArticle(requestDto);
        return article;
    }

    @GetMapping("/articles")
    public @ResponseBody List<Article> getArticle(){
        return articleService.getArticle();
    }

    @GetMapping("/articles/read")
    public String readArticle(@RequestParam("id") Long id) {
        return "view";
    }

}
