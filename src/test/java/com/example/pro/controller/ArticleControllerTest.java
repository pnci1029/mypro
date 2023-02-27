package com.example.pro.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest @RestController
public class ArticleControllerTest {
    Mock mock;

    ArticleController articleController;

    @Test
    void 게시글_테스트1() {
        ArticleController article = mock(ArticleController.class);
        when(article.getArticles()).thenReturn("전체 조회1");
        String result = article.getArticles();
        Assertions.assertEquals(result,"전체 조회1");
    }

    @Test
    void 게시글_테스트2() {
        ArticleController article = mock(ArticleController.class);
        when(article.articlePost()).thenReturn("포스트");
        String result = article.articlePost();
        Assertions.assertEquals("포스트",result);
    }

}
