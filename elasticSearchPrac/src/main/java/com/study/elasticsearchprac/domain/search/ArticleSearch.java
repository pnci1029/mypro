package com.study.elasticsearchprac.domain.search;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.List;

// 인덱스 명 소문자로 해야함
@Document(indexName = "article")
@Getter @Builder
public class ArticleSearch {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String content;
    @Field(type = FieldType.Text)
    private String img;

    @Field(type = FieldType.Text)
    private List<String> imgTagging;
}
