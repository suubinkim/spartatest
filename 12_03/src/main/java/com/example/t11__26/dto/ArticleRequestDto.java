package com.example.t11__26.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ArticleRequestDto {
    private String title;
    private String content;
    private String tags;
    private MultipartFile image;
}

