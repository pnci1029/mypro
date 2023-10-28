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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

@Slf4j
public class ArticleServiceTest {

    @InjectMocks
    ArticleService articleService;
    @Mock
    ArticleRepository articleRepository;
    @Mock
    Article mockArticle;

    Article article;



    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.initMocks(this);
        article = Article.builder()
                .id(1L)
                .articleStatus(ArticleStatus.Etc)
                .view(0)
                .title("testTitle")
                .contents("testContents")
                .build();
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

        given(articleRepository.save(any(Article.class))).willReturn(mockArticle);

        articleService.create(article);

        Mockito.verify(articleRepository, times(1)).save(any());
    }


    @DisplayName("게시글 조회")
    @Test
    void findArticleById() {
        given(articleRepository.findById(1L)).willReturn(Optional.ofNullable(mockArticle));

        Article article = articleService.findArticle(1L);

        assertThat(article).isNotNull();
    }
    @DisplayName("게시글 ID로 조횟수 증가 O")
    @Test
    void findByArticleId() {
        ArticleRequestDto article = ArticleRequestDto.builder()
                .title("my title")
                .contents("my contents")
                .articleStatus(String.valueOf(ArticleStatus.Health))
                .build();

        given(articleRepository.save(any(Article.class))).willReturn(mockArticle);

        articleService.create(article);

        given(articleRepository.findById(1L)).willReturn(Optional.ofNullable(mockArticle));

//        verify(articleRepository,times(1)).findById(1L);

    }
}
