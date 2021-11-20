package com.example.t11_19.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Setter
@Getter
public class CommentRequestDto {
    private Long idx;
    private String comment;
}
