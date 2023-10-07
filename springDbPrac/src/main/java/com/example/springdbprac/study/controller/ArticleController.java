package com.example.springdbprac.study.controller;

import com.example.springdbprac.study.dto.requestDto.ArticleRequestDto;
import com.example.springdbprac.study.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/create")
    public void createArticle(@RequestBody ArticleRequestDto dto) {
        articleService.create(dto);
    }
}
