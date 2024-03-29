package com.example.springdbprac.study.controller;

import com.example.springdbprac.study.domain.entity.Article;
import com.example.springdbprac.study.domain.entity.ArticleStatus;
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
@RequestMapping("/api")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/article/create")
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequestDto dto) {
        articleService.create(dto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/article/creates")
    public ResponseEntity<?> createArticles() {
        for (int i = 0; i < 10000; i++) {
            ArticleRequestDto dto = ArticleRequestDto.builder()
                    .articleStatus("Etc")
                    .title("title" + i)
                    .contents("contents" + i)
                    .build();

            articleService.create(dto);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/article")
    public ResponseEntity<Article> searchArticle(@RequestParam(name = "title") String title) {
        return ResponseEntity.ok(articleService.searchArticleTitle(title));
    }

    @GetMapping("/article/{articleId}")
    public ResponseEntity<Article> getArticle(@PathVariable(name = "articleId") Long articleId) {
        return ResponseEntity.ok(articleService.findArticle(articleId));
    }

    @GetMapping("/article")
    public ResponseEntity<Slice<ArticleResponseDto>> getArticles(Pageable pageable) {
        return ResponseEntity.ok(articleService.findArticles(pageable));
    }

    @GetMapping("/article/search")
    public ResponseEntity<Slice<ArticleResponseDto>> searchArticle(@RequestParam(required = false)Long id,
                                       @RequestParam(required = false)String title,
                                       @RequestParam(required = false)String contents,
                                                                   Pageable pageable) {
        return ResponseEntity.ok(articleService.searchArticles(id, title, contents, pageable));
    }
}
