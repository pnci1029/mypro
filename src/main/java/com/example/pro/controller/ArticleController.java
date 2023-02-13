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

    private ArticleService articleService;

    @PostMapping("/post")
    public void articlePost() {
        articleService.postArticle();
    }
}
