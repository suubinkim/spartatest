package com.example.t11__26.domain;

import com.example.t11__26.dto.CommentRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @Column(nullable = false)
    private String comment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "article_idx", nullable = false)
    private Article article;

    public Comment(CommentRequestDto requestDto, Article article) {
        this.comment = requestDto.getComment();
        this.article = article;
    }
}