package com.study.elasticsearchprac.service;

import com.study.elasticsearchprac.domain.Article;
import com.study.elasticsearchprac.domain.ArticleRepository;
import com.study.elasticsearchprac.domain.search.ArticleSearch;
import com.study.elasticsearchprac.domain.search.ArticleSearchRepository;
import com.study.elasticsearchprac.domain.search.ImageRepository;
import com.study.elasticsearchprac.dto.ArticleRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service @Slf4j @RequiredArgsConstructor
public class ArticleService {
    private final ArticleSearchRepository articleSearchRepository;
    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;

    public void create(ArticleRequestDto articleRequestDto, MultipartFile multipartFiles) {
//        multipartFiles.forEach(data -> log.info(data.getOriginalFilename()));
        log.info(multipartFiles.getOriginalFilename());

        log.info(articleRequestDto.getTitle());

        // article
        articleRepository.save(Article.builder()
                .title(articleRequestDto.getTitle())
                .content(articleRequestDto.getContent())
                .img(multipartFiles.getOriginalFilename())
                .build());

        // elastic
        articleSearchRepository.save(ArticleSearch.builder()
                .title(articleRequestDto.getTitle())
                .content(articleRequestDto.getContent())
                .img(multipartFiles.getOriginalFilename())
                .imgTagging(new ArrayList<>())
                .build());
    }

    public List<Article> getAllArticles() {
//        Iterable<ArticleSearch> all = articleSearchRepository.findAll();

        return articleRepository.findAll();
    }
}
