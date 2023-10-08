package com.example.springdbprac.study.service;

import com.example.springdbprac.study.domain.entity.Article;
import com.example.springdbprac.study.domain.entity.ArticleStatus;
import com.example.springdbprac.study.domain.repository.ArticleRepository;
import com.example.springdbprac.study.dto.requestDto.ArticleRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@Slf4j
public class ArticleServiceTest {

    @InjectMocks
    ArticleService articleService;
    @Mock
    ArticleRepository articleRepository;
    @Mock
    Article article;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.initMocks(this);
//        articleRepository.save(Article.builder()
//                .title("title")
//                .contents("contents")
//                .view(0)
//                .build());

    }


    @DisplayName("게시글 생성")
    @Test
    void createArticle() {
        ArticleRequestDto article = ArticleRequestDto.builder()
                .title("my title")
                .contents("my contents")
                .articleStatus(String.valueOf(ArticleStatus.Health))
                .build();

        given(articleRepository.save(any(Article.class))).willReturn(mock(Article.class));

        articleService.create(article);

        Mockito.verify(articleRepository, times(1)).save(any());
    }

    @DisplayName("게시글 ID로 조횟수 증가 O")
    @Test
    void findByArticleId() {

        ArticleRequestDto article = ArticleRequestDto.builder()
                .title("my title")
                .contents("my contents")
                .articleStatus(String.valueOf(ArticleStatus.Health))
                .build();

//        given(articleRepository.save(any(Article.class))).willReturn(mock(Article.class));
//        articleService.findArticle(1L);

//        given(articleService.findArticle(eq(1L))).willReturn(article);


    }
}
