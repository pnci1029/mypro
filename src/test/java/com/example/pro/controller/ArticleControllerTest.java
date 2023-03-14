package com.example.pro.controller;

import com.example.pro.domain.entity.Article;
import com.example.pro.domain.entity.Member;
import com.example.pro.domain.repository.ArticleRepository;
import com.example.pro.domain.repository.MemberRepository;
import com.example.pro.dto.article.articleRequestDto.ArticleRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.transaction.Transactional;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest @Transactional
public class ArticleControllerTest {
    @Autowired
    MockMvc mockMvc;
    ObjectMapper om = new ObjectMapper();
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ArticleRepository articleRepository;
    @BeforeEach
    void 회원_생성() {
        memberRepository.deleteAll();
        Member member1 = new Member("user1", 20);
        Member member2 = new Member("이이", 15);
        memberRepository.save(member1);
        memberRepository.save(member2);
    }
    @Test
    void 개시글_조회() {
        Article article = new Article("title","content",100,100,100);

        articleRepository.save(article);

        try {
            mockMvc.perform(
                            get("/article/articles")
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andDo(print())
                    .andExpect(content().string(om.writeValueAsString(articleRepository.findAll())))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new IllegalStateException("error");
        }
    }

    @Test
    void 게시글_작성_예외처리_NOT_NULL_실패_테스트() {
        ArticleRequestDto target = ArticleRequestDto.builder()
                .title(null)
                .content("content")
                .dl(100)
                .sq(20)
                .bc(50)
                .build();
        try {
            this.mockMvc.perform(post("/article/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(om.writeValueAsString(target)))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(value -> Assertions.assertTrue(value.getResolvedException() instanceof MethodArgumentNotValidException));
        } catch (Exception e) {
            throw new IllegalStateException("error");
        }
    }

    @Test
    void 게시글_작성_예외처리_MIN_테스트() {
        ArticleRequestDto target = ArticleRequestDto.builder()
                .title("title")
                .content("content")
                .dl(100)
                .sq(20)
                .bc(50)
                .build();
        try {
            this.mockMvc.perform(post("/article/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(om.writeValueAsString(target)))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(value -> Assertions.assertTrue(value.getResolvedException() instanceof MethodArgumentNotValidException));
        } catch (Exception e) {
            throw new IllegalStateException("error");
        }
    }

    @Test
    void 게시글_작성_성공_테스트() throws Exception {
        ArticleRequestDto target = ArticleRequestDto.builder()
                .title("title")
                .content("content")
                .dl(100)
                .sq(50)
                .bc(120)
                .build();

        this.mockMvc.perform(post("/article/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(target)))
                .andDo(print())
                // 최신순 정렬 후 가장 최신 값 조회 비교
                .andExpect(content().string(om.writeValueAsString(articleRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).get(0))))
                .andExpect(status().isOk());
    }

    @Test
    void 게시글_작성_테스트() {
        ArticleRequestDto target = ArticleRequestDto.builder()
                .content("content")
//                .title("title")
                .bc(10)
                .sq(150)
                .dl(200)
                .build();
        ArticleController article = mock(ArticleController.class);
//        when(article.articlePost(target)).thenReturn(articleRepository.);
//        String result = article.articlePost(target);
//        System.out.println(result);

        try {
            this.mockMvc.perform(post("/article/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(om.writeValueAsString(target)))
                    .andDo(print())
                    .andExpect(value -> Assertions.assertTrue(value.getResolvedException() instanceof MethodArgumentNotValidException));
        } catch (Exception e) {
            throw new IllegalStateException("error");
        }
    }

    @Test
    void 게시글_저장() {

    }

}
