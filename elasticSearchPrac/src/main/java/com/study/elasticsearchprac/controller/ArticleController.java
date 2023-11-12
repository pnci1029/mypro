package com.study.elasticsearchprac.controller;

import com.study.elasticsearchprac.dto.ArticleRequestDto;
import com.study.elasticsearchprac.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping(value = "/article/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String createArticle(@RequestPart(required = false) ArticleRequestDto articleRequestDto,
                                @RequestPart(name = "multipartFiles", required = false) MultipartFile multipartFiles) {
        articleService.create(articleRequestDto, multipartFiles);
        return "/article/articleMain";
    }

    @GetMapping("/article")
    public String getArticle() {
        return "/article/articleMain";
    }
}
