package com.example.t12_24.service;


import com.example.t12_24.domain.Article;
import com.example.t12_24.domain.Comment;
import com.example.t12_24.dto.ArticleRequestDto;
import com.example.t12_24.dto.CommentRequestDto;
import com.example.t12_24.repository.ArticleRepository;
import com.example.t12_24.repository.CommentRepository;
import com.example.t12_24.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final S3Uploader s3Uploader;

    public Page<Article> getArticle(String tags, Pageable pageable) {
        if (tags.isEmpty()) {
            return articleRepository.findAll(pageable);
        } else {
            return articleRepository.findArticlesByTagsContaining(tags, pageable);
        }
    }

    @Transactional
    public Article createArticle(ArticleRequestDto requestDto) throws IOException {
        // 이미지 AWS S3 업로드
        String uploadImageUrl = s3Uploader.upload(requestDto.getImage(), "article");
        Article article = new Article(requestDto, uploadImageUrl);
        articleRepository.save(article);
        return article;
    }

    public Article readArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
    }

    public void writeComment(CommentRequestDto requestDto) {
        Article article = articleRepository.findById(requestDto.getIdx()).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        Comment comment = new Comment(requestDto, article);
        commentRepository.save(comment);
    }

}
