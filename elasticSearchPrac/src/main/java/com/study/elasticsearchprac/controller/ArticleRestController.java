package com.study.elasticsearchprac.controller;

import com.study.elasticsearchprac.dto.ArticleRequestDto;
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
    private ArticleService articleService;

    @PostMapping(value = "/create/article", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public String createArticle(@RequestPart(required = false) ArticleRequestDto articleRequestDto,
                                @RequestPart(required = false) List<MultipartFile> multipartFiles) {
        if (!multipartFiles.isEmpty()) {
            for (MultipartFile multipartFile : multipartFiles) {
                System.out.println("multipartFile.getName() = " + multipartFile.getName());
            }
        }
        return "success";
    }

    @GetMapping("/get")
    public String getArticle() {
        return "1";
    }
}
