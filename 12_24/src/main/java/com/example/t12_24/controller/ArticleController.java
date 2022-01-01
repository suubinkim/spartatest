package com.example.t12_24.controller;


import com.example.t12_24.domain.Article;
import com.example.t12_24.dto.ArticleRequestDto;
import com.example.t12_24.dto.CommentRequestDto;
import com.example.t12_24.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Article> getArticle(@RequestParam("searchTag") String tags,
                                    Pageable pageable) {
        return articleService.getArticle(tags, pageable);
    }

    @GetMapping("/articles/read/{id}")
    public Article readArticle(@PathVariable Long id) {
        return articleService.readArticle(id);
    }

    @PostMapping("/articles/comment")
    public void writeComment(@RequestBody CommentRequestDto requestDto) {
        articleService.writeComment(requestDto);

    }

}
