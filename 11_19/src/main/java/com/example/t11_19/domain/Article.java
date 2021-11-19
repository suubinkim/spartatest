package com.example.t11_19.domain;

import com.example.t11_19.dto.ArticleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;


    public Article(ArticleRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

}
