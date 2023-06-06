package com.example.pro.controller;

import com.example.pro.domain.entity.Article;
import com.example.pro.dto.article.articleRequestDto.ArticleRequestDto;
import com.example.pro.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public List<Article> getArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/log")
    public String log() {
        log.trace("log Trace");
        log.debug("log debug");
        log.info("log info");
        log.warn("log warn");
        log.error("log error");
        return "전체 조회";
    }

    @PostMapping("/post")
    public Article articlePost(@RequestBody @Valid ArticleRequestDto articleRequestDto) {
        return articleService.postArticle(articleRequestDto);
    }

    @PatchMapping("/patch")
    public String articlePut(@RequestBody @Valid ArticleRequestDto articleRequestDto) {
        articleService.patchArticle(articleRequestDto);

        return null;
    }
}
