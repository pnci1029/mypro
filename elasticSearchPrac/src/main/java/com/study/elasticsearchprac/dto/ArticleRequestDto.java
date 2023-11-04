package com.study.elasticsearchprac.dto;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ArticleRequestDto {
    private String title;
    private String content;
}
