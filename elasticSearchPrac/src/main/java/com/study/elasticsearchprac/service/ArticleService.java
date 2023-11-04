package com.study.elasticsearchprac.service;

import com.study.elasticsearchprac.domain.search.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service @Slf4j @RequiredArgsConstructor
public class ArticleService {
    private ArticleRepository articleRepository;
}
