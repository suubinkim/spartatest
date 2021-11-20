package com.example.t11_19.controller;

import com.example.t11_19.domain.Article;
import com.example.t11_19.domain.Comment;
import com.example.t11_19.dto.ArticleRequestDto;
import com.example.t11_19.dto.CommentRequestDto;
import com.example.t11_19.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/articles")
    public Article createArticle(@RequestBody ArticleRequestDto requestDto){
        Article article = articleService.createArticle(requestDto);
        return article;
    }

    @GetMapping("/articles")
    public List<Article> getArticle(){
        return articleService.getArticle();
    }

    @GetMapping("/articles/read/{id}")
    public Article readArticle(@PathVariable Long id){
        return articleService.readArticle(id);
    }

    @PostMapping("/articles/comment")
    public void writeComment(@RequestBody CommentRequestDto requestDto){
        articleService.writeComment(requestDto);

    }

}
