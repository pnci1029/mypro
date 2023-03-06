package com.example.pro.dto.article.articleRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class ArticleRequestDto {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull @Min(0)
    private int sq;
    @NotNull @Min(0)
    private int bc;
    @NotNull @Min(0)
    private int dl;
    private Long visitCount;
}
