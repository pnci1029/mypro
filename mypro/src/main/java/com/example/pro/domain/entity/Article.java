package com.example.pro.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Builder @Getter @NoArgsConstructor @AllArgsConstructor
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private int sq;
    private int bc;
    private int dl;
    private Long visitCount;

    public Article(String title, String content, int sq, int bc, int dl) {
        this.title = title;
        this.content = content;
        this.sq = sq;
        this.bc = bc;
        this.dl = dl;
//        this.visitCount = visitCount;
//        this.member = member;
    }

//    @ManyToOne
//    @JoinColumn(referencedColumnName = "id")
//    private Member member;

}
