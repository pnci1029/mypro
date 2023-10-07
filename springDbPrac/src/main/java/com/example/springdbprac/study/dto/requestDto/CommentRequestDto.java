package com.example.springdbprac.study.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class CommentRequestDto {
    private String writer;
    private String contents;
    private Long articleId;
}
