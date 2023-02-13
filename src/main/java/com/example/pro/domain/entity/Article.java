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
    private String name;
    private String content;
    private int sq;
    private int bc;
    private int dl;
    private Long visitCount;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Member member;

}
