package com.example.pro.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity @Getter @NoArgsConstructor @AllArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String joinPath;

    @OneToMany(mappedBy = "member")
    private List<Article> article;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
