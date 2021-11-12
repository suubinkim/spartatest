package com.sparta.t11_12.domain;

import com.sparta.t11_12.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Memo {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String content;

    public Memo(MemoRequestDto requestDto){
        this.content = requestDto.getContent();
    }
}
