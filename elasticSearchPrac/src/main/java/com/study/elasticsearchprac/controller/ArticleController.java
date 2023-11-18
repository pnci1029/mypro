package com.study.elasticsearchprac.controller;

import com.study.elasticsearchprac.domain.search.ArticleSearch;
import com.study.elasticsearchprac.dto.ArticleRequestDto;
import com.study.elasticsearchprac.dto.ImageTagResponseDto;
import com.study.elasticsearchprac.service.ArticleSearchService;
import com.study.elasticsearchprac.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final ArticleSearchService articleSearchService;

    @PostMapping(value = "/article/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String createArticle(@RequestPart(required = false,name = "title") String title,
                                @RequestPart(required = false,name = "content") String content,
                                @RequestPart(name = "multipartFiles", required = false) MultipartFile multipartFiles) {

        articleService.create(ArticleRequestDto.builder().title(title).content(content).build(), multipartFiles);
        log.info(title);
        return "/article/articleMain";
    }

    @GetMapping("/article")
    public String getArticles(Model model) {
        // h2
//        model.addAttribute("article", articleService.getAllArticles());

        // elastic
        model.addAttribute("article", articleSearchService.getAllArticles());
        return "/article/articleMain";
    }

    @GetMapping("/article/tag")
    public ArticleSearch getArticleTag(@RequestParam String imgId) {
        return articleSearchService.searchArticleTag(imgId);
    }

    @GetMapping("/article/image")
    public String getImageInfo(Model model) {
        model.addAttribute("image", articleSearchService.getAllImageInfo());

        return "/article/articleImage";
    }

    @GetMapping("/article/search/{keyword}")
    public String getAllArticleTags(@PathVariable String keyword, Model model) {

        model.addAttribute("article", articleSearchService.searchArticle(keyword));
        return "/article/articleMain";

    }

    @PutMapping("/article/tagging")
    public String articleTag(ImageTagResponseDto dto) {
        log.info(dto.getImageName());
        articleSearchService.tagImage(dto.getImageTag(), dto.getImageName());
        return "/article/articleImage";
    }
}
