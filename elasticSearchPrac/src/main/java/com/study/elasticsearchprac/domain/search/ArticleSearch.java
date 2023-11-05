package com.study.elasticsearchprac.domain.search;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// 인덱스 명 소문자로 해야함
@Document(indexName = "article")
@Getter @Builder
public class ArticleSearch {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(type = FieldType.Keyword)
    private Long id;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String content;
    @Field(type = FieldType.Text)
    private String img;
}
