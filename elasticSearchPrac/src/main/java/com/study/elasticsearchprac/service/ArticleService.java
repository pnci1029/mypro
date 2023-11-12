package com.study.elasticsearchprac.service;

import com.study.elasticsearchprac.domain.search.ArticleSearchRepository;
import com.study.elasticsearchprac.domain.search.ImageRepository;
import com.study.elasticsearchprac.dto.ArticleRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service @Slf4j @RequiredArgsConstructor
public class ArticleService {
    private final ArticleSearchRepository articleRepository;
    private final ImageRepository imageRepository;

    public void create(ArticleRequestDto articleRequestDto, MultipartFile multipartFiles) {
//        multipartFiles.forEach(data -> log.info(data.getOriginalFilename()));
        log.info(multipartFiles.getOriginalFilename());
    }
}
