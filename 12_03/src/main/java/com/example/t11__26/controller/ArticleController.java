package com.example.t11__26.controller;

import com.example.t11__26.domain.Article;
import com.example.t11__26.dto.ArticleRequestDto;
import com.example.t11__26.dto.CommentRequestDto;
import com.example.t11__26.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/articles")
    public Article createArticle(@ModelAttribute ArticleRequestDto requestDto) throws IOException {
        return articleService.createArticle(requestDto);
    }

    @GetMapping("/articles")
    public List<Article> getArticle(@RequestParam("searchTag")String tags){
        return articleService.getArticle(tags);
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
