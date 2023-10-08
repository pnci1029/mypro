package com.example.springdbprac.study.dto.responseDto;

import com.example.springdbprac.study.domain.entity.ArticleStatus;
import com.example.springdbprac.study.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ArticleResponseDto {
    private Long id;
    private String title;
    private String contents;
    private long view;
    private ArticleStatus articleStatus;
    private List<Comment> comments;

    public ArticleResponseDto(Long id, String title, String contents, long view, ArticleStatus articleStatus) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.view = view;
        this.articleStatus = articleStatus;
    }
}
