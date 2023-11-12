package com.study.elasticsearchprac.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Builder
public class ArticleRequestDto {
    private String title;
    private String content;
//    private MultipartFile img;
}
