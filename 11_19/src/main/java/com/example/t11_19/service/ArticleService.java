package com.example.t11_19.service;

import com.example.t11_19.domain.Article;
import com.example.t11_19.dto.ArticleRequestDto;
import com.example.t11_19.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getArticle(){
        return articleRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public Article createArticle(ArticleRequestDto requestDto){
        Article article = new Article(requestDto);
        articleRepository.save(article);
        return article;
    }

}
