package com.example.springdbprac.study.domain.entity;

import com.example.springdbprac.study.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter @NoArgsConstructor @AllArgsConstructor @Builder
@SequenceGenerator(
        name = "COMMENT_SEQ_GEN",
        sequenceName = "COMMENT_SEQ",
        initialValue = 1,
        allocationSize = 30
)
public class Comment extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ")
    @Column(name = "COMMENT_ID")
    private Long id;
    private String writer;
    private String contents;
    @ManyToOne @JsonManagedReference
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;
}
