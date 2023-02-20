package com.example.pro.controller;

import com.example.pro.domain.HelloController;
import com.example.pro.domain.entity.Article;
import com.example.pro.domain.entity.Member;
import com.example.pro.domain.repository.ArticleRepository;
import com.example.pro.domain.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ArticleControllerTest {

    MockMvc mock;
    @Autowired
    HelloController hello;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void member() {
        Member member1 = new Member("김김김", 20);
        Member member2 = new Member("이이이", 25);
        memberRepository.save(member2);
        memberRepository.save(member1);
    }

    @Test
    void articleBasicTest() {
        Member member3 = new Member("최최최", 30);
        Member save = memberRepository.save(member3);


        assertThat(save.getAge()).isEqualTo(30);

    }
}
