package com.example.pro.dto.article.articleRequestDto;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

@SpringBootTest
public class ArticleRequestDtoTest {


    @DisplayName("낫널 예외처리")
    @Test
    void notNullTest() {
        ArticleRequestDto.builder()
                .title(null)
                .content("content")
                .bc(100)
                .sq(100)
                .dl(100)
                .visitCount(1L)
                .build();

        Assertions.assertThrows(MethodArgumentNotValidException.class,
                () -> {
                    ArticleRequestDto.builder()
                            .title(null)
                            .content("content")
                            .bc(100)
                            .sq(100)
                            .dl(100)
                            .visitCount(1L)
                            .build();
                });
    }

}
