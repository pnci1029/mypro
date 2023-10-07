package com.example.springdbprac.study.dto.requestDto;

import com.example.springdbprac.study.domain.entity.ArticleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ArticleRequestDto {
    private String title;
    private String contents;
    private String articleStatus;
}
