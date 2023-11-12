package com.study.elasticsearchprac.controller;

import com.study.elasticsearchprac.domain.search.ArticleSearch;
import com.study.elasticsearchprac.dto.ArticleRequestDto;
import com.study.elasticsearchprac.service.ArticleSearchService;
import com.study.elasticsearchprac.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/rest")
@RestController @RequiredArgsConstructor @Slf4j
public class ArticleRestController {
    private final ArticleService articleService;
    private final ArticleSearchService articleSearchService;

    @PostMapping(value = "/article/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String createArticle(@RequestPart(required = false) ArticleRequestDto articleRequestDto,
                                @RequestPart(name = "multipartFiles", required = false) MultipartFile multipartFiles) {
        articleService.create(articleRequestDto, multipartFiles);
        return "success";
    }

    @GetMapping("/get")
    public String getArticle() {
        return "1";
    }

    @GetMapping("/search/articles")
    public List<ArticleSearch> getArticles() {
        return articleSearchService.getAllArticles();
    }
}
