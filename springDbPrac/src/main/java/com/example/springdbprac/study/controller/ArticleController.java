package com.example.springdbprac.study.controller;

import com.example.springdbprac.study.domain.entity.Article;
import com.example.springdbprac.study.dto.requestDto.ArticleRequestDto;
import com.example.springdbprac.study.dto.responseDto.ArticleResponseDto;
import com.example.springdbprac.study.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequestDto dto) {
        articleService.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<Article> getArticle(@PathVariable(name = "articleId") Long articleId) {
        return ResponseEntity.ok(articleService.findArticle(articleId));
    }

    @GetMapping("/")
    public ResponseEntity<Slice<ArticleResponseDto>> getArticles(Pageable pageable) {
        return ResponseEntity.ok(articleService.findArticles(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Slice<ArticleResponseDto>> searchArticle(@RequestParam(required = false)Long id,
                                       @RequestParam(required = false)String title,
                                       @RequestParam(required = false)String contents,
                                                                   Pageable pageable) {
        return ResponseEntity.ok(articleService.searchArticles(id, title, contents, pageable));
    }
}
