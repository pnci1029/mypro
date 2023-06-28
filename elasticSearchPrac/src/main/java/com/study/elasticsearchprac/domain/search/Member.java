package com.study.elasticsearchprac.domain.search;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Document(indexName = "elastic")
@Getter @Data @Builder
public class Member {
    @Id @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String age;
    @Field(type = FieldType.Text)
    private String home;
}
