package com.example.pro.domain.entity;

import com.example.pro.domain.HelloController;
import com.example.pro.domain.repository.ArticleRepository;
import com.example.pro.domain.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.*;

import static org.assertj.core.api.Assertions.assertThat;

@Entity @Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private int sq;
    private int bc;
    private int dl;
    private Long visitCount;
//    @ManyToOne
//    @JoinColumn(referencedColumnName = "id")
//    private Member member;

    public Article(String title, String content, int sq, int bc, int dl) {
        this.title = title;
        this.content = content;
        this.sq = sq;
        this.bc = bc;
        this.dl = dl;
//        this.visitCount = visitCount;
//        this.member = member;
    }
//    MockMvc mock;
//    @Autowired
//    HelloController hello;
//    @Autowired
//    ArticleRepository articleRepository;
//    @Autowired
//    MemberRepository memberRepository;
//
//    @BeforeEach
//    void member() {
//        Member member1 = new Member("김김김", 20);
//        Member member2 = new Member("이이이", 25);
//        memberRepository.save(member2);
//        memberRepository.save(member1);
//    }
//
//    @Test
//    void articleBasicTest() {
//        Member member3 = new Member("최최최", 30);
//        Member save = memberRepository.save(member3);
//
//
//        assertThat(save.getAge()).isEqualTo(30);
//
//    }
}
