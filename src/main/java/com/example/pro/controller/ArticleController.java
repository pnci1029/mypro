package com.example.pro.controller;

import com.example.pro.sertvice.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/article")
@RequiredArgsConstructor @Slf4j
public class ArticleController {

    private final ArticleService articleService;
    @GetMapping("/articles")
    public String getArticles() {
        log.trace("log Trace");
        log.debug("log debug");
        log.info("log info");
        log.warn("log warn") ;
        log.error("log error"); ;
        return "전체 조회";
    }

    @PostMapping("/post")
    public String articlePost() {
        articleService.postArticle();
        return "postSuccess";
    }
}
