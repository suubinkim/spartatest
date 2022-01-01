package com.example.t12_24.repository;


import com.example.t12_24.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findAllByOrderByCreatedAtDesc();
    Page<Article> findArticlesByTagsContaining(String tags, Pageable pageable);
}
