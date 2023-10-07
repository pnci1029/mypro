package com.example.springdbprac.study.service;

import com.example.springdbprac.study.domain.entity.Article;
import com.example.springdbprac.study.domain.entity.ArticleStatus;
import com.example.springdbprac.study.domain.repository.ArticleRepository;
import com.example.springdbprac.study.dto.requestDto.ArticleRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service @Slf4j @RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void create(ArticleRequestDto dto) {
        articleRepository.save(Article.builder()
                .title(dto.getTitle())
                .contents(dto.getContents())
                .articleStatus(ArticleStatus.valueOf(dto.getArticleStatus()))
                .build());
    }
}
