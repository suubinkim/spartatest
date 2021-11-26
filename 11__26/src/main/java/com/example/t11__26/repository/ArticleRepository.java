package com.example.t11__26.repository;

import com.example.t11__26.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findAllByOrderByCreatedAtDesc();
    List<Article> findArticlesByTagsContaining(String tags);
}
