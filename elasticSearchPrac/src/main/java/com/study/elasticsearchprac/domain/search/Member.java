package com.study.elasticsearchprac.domain.search;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Document(indexName = "member")
@Getter @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Member {
    @Id @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Keyword)
    private int age;
    @Field(type = FieldType.Keyword)
    private String home;
    @Field(type = FieldType.Keyword)
    private String gender;

}
