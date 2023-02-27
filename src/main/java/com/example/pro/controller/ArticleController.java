package com.example.pro.controller;

import com.example.pro.sertvice.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    @GetMapping("/articles")
    public String getArticles() {
        return "전체 조회";
    }

    @PostMapping("/post")
    public String articlePost() {
        articleService.postArticle();
        return "postSuccess";
    }
}
