package com.example.t12_17.service;

import com.example.t12_17.domain.Article;
import com.example.t12_17.domain.Comment;
import com.example.t12_17.dto.ArticleRequestDto;
import com.example.t12_17.dto.CommentRequestDto;
import com.example.t12_17.repository.ArticleRepository;
import com.example.t12_17.repository.CommentRepository;
import com.example.t12_17.util.S3Uploader;
import lombok.RequiredArgsConstructor;
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

    public List<Article> getArticle(String tags){
        return articleRepository.findArticlesByTagsContaining(tags);
    }

    @Transactional
    public Article createArticle(ArticleRequestDto requestDto) throws IOException {
        // 이미지 AWS S3 업로드
        String uploadImageUrl = s3Uploader.upload(requestDto.getImage(), "article");
        Article article = new Article(requestDto,uploadImageUrl);
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
