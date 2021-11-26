package com.example.t11__26.service;

import com.example.t11__26.domain.Article;
import com.example.t11__26.domain.Comment;
import com.example.t11__26.dto.ArticleRequestDto;
import com.example.t11__26.dto.CommentRequestDto;
import com.example.t11__26.repository.ArticleRepository;
import com.example.t11__26.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public List<Article> getArticle(String tags){
        return articleRepository.findArticlesByTagsContaining(tags);
    }

    @Transactional
    public Article createArticle(ArticleRequestDto requestDto){
        Article article = new Article(requestDto);
        articleRepository.save(article);
        return article;
    }

    public Article readArticle(Long id){
        return articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
    }

    public void writeComment(CommentRequestDto requestDto){
        Article article = articleRepository.findById(requestDto.getIdx()).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        Comment comment = new Comment(requestDto,article);
        commentRepository.save(comment);
    }

}
