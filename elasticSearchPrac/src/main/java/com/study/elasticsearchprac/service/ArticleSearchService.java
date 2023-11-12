package com.study.elasticsearchprac.service;

import com.study.elasticsearchprac.domain.search.ArticleSearch;
import com.study.elasticsearchprac.domain.search.ArticleSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j @RequiredArgsConstructor
public class ArticleSearchService {

    private final ArticleSearchRepository articleSearchRepository;
    public List<ArticleSearch> getAllArticles() {
        return articleSearchRepository.findAllArticles();
    }
}
