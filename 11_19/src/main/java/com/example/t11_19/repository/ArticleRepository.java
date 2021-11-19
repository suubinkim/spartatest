package com.example.t11_19.repository;

import com.example.t11_19.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findAllByOrderByCreatedAtDesc();
}
