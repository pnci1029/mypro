package com.example.springdbprac.study.controller;

import com.example.springdbprac.study.domain.entity.Article;
import com.example.springdbprac.study.domain.entity.Comment;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j @AutoConfigureMockMvc
public class CommentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper om;

    @Test
    @DisplayName("댓글 생성")
    void createComment() throws Exception {
        Comment comment = Comment.builder()
                .article(new Article())
                .writer("test writer")
                .contents("test content1")
                .build();

        mockMvc.perform(
                        post("/api/comment/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(om.writeValueAsString(comment))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}
