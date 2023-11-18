package com.study.elasticsearchprac.controller;

import com.study.elasticsearchprac.domain.search.ArticleSearch;
import com.study.elasticsearchprac.dto.ArticleRequestDto;
import com.study.elasticsearchprac.dto.ImageTagResponseDto;
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

    // 생성
    @PostMapping(value = "/article/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String createArticle(@RequestPart(required = false) ArticleRequestDto articleRequestDto,
                                @RequestPart(name = "multipartFiles", required = false) MultipartFile multipartFiles) {
        articleService.create(articleRequestDto, multipartFiles);
        return "success";
    }

    // 전체조회
    @GetMapping("/article/searches")
    public List<ArticleSearch> getArticles() {
        return articleSearchService.getAllArticles();
    }


    // 엘라스틱 id로 조회
    @GetMapping("/article/search/{articleId}")
    public ArticleSearch getArticleById(@PathVariable String articleId) {
        return articleSearchService.getArticleById(articleId);
    }

    // 제목 / 내용 / 태깅으로 검색
    @GetMapping("/article/search")
    public List<ArticleSearch> getArticleTagging(@RequestParam String keyword) {
        return articleSearchService.searchArticle(keyword);
    }

    // 전체 이미지 & 태깅정보 조회
    @GetMapping("/article/images")
    public List<ImageTagResponseDto> articleImages() {
        return articleSearchService.getAllImageInfo();
    }

    // 이미지 이름으로 태깅 정보 조
    @GetMapping("/article/tag")
    public ArticleSearch getArticleTag(@RequestParam String imgId) {
        return articleSearchService.searchArticleTag(imgId);
    }

    // 태깅정보 추가
    @PostMapping("/article/tagging")
    public void tagImage(@RequestParam(required = false) List<String> tagging,
                         @RequestParam(required = false) String img) {
        articleSearchService.tagImage(tagging, img);
    }
}
