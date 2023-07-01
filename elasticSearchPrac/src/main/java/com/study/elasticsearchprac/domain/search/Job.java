package com.study.elasticsearchprac.domain.search;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Document(indexName = "job")
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String money;
}
