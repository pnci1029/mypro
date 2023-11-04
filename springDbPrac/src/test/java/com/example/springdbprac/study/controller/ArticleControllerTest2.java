package com.example.springdbprac.study.controller;

import com.example.springdbprac.study.domain.entity.Article;
import com.example.springdbprac.study.domain.entity.ArticleStatus;
import com.example.springdbprac.study.domain.repository.ArticleRepository;
import com.example.springdbprac.study.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ArticleControllerTest2 {
    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleRepository articleRepository;

    @BeforeEach
    void beforeEach() {
        for (int i = 0; i < 10; i++) {
            articleRepository.save(new Article((long) i, "test + " + i, "contents + " + i, 0, ArticleStatus.Health, null));
        }
    }

    @Test
    @DisplayName("게시글 생성")
    void createArticles() {
        Article article1 = new Article(1L, "title", "contents", 0, ArticleStatus.Etc, null);
        Article article2 = new Article(2L, "title", "contents", 0, ArticleStatus.Etc, null);
        Article article3 = new Article(3L, "title", "contents", 0, ArticleStatus.Etc, null);

        articleRepository.saveAll(List.of(article1, article2, article3));

        List<Article> articles = articleRepository.findAll();
        assertThat(articles).hasSize(3);
    }
}
