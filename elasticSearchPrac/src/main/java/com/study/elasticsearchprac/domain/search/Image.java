package com.study.elasticsearchprac.domain.search;

import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Document(indexName = "article")
@Getter
public class Image {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(type = FieldType.Keyword)
    private String Id;

    @Field(type = FieldType.Text)
    private String imageName;
    @Field(type = FieldType.Nested)
    private List<String> imageTagging;
}
