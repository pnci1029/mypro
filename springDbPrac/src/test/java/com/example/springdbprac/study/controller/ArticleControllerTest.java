package com.example.springdbprac.study.controller;

import com.example.springdbprac.study.domain.entity.Article;
import com.example.springdbprac.study.domain.entity.ArticleStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest @AutoConfigureMockMvc
public class ArticleControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper om;

    @DisplayName("게시글 생성 테스트")
    @Test
    void createArticle() throws Exception {
        Article article = Article.builder()
                .articleStatus(ArticleStatus.Daily)
                .title("title test1")
                .contents("test content1")
                .build();

        mockMvc.perform(
                post("/api/article/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(article))
        )
                .andExpect(status().isOk());

    }
}
