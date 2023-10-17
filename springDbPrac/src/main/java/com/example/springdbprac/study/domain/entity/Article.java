package com.example.springdbprac.study.domain.entity;

import com.example.springdbprac.study.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity @SequenceGenerator(
        name = "ARTICLE_SEQ_GEN",
        sequenceName = "ARTICLE_SEQ",
        initialValue = 1,
        allocationSize = 30
)
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "ARTICLE", indexes = {@Index(name = "IX_ARTICLE_ID", columnList = "ARTICLE_ID")})
public class Article extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_SEQ")
    @Column(name = "ARTICLE_ID")
    private Long id;
    private String title;
    private String contents;
    private long view;
    @Enumerated(EnumType.STRING)
    private ArticleStatus articleStatus;
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Comment> comments;

    public void viewIncrease() {
        this.view++;
    }

}
