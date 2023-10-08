package com.example.springdbprac.study.service;

import com.example.springdbprac.study.domain.entity.Article;
import com.example.springdbprac.study.domain.entity.ArticleStatus;
import com.example.springdbprac.study.domain.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class ArticleServiceTest {

    @InjectMocks
    ArticleService articleService;

    @Mock
    ArticleRepository articleRepository;

    @BeforeEach
    void beforeEach() {
        for (int i = 0; i < 10; i++) {
            Article.builder()
                    .title("title" + 1 + "test")
                    .contents("contents" + 1 + "test")
                    .articleStatus(ArticleStatus.Develope)
                    .build();
        }

    }


    @DisplayName("게시글 생성")
    void createArticle() {

    }
}
