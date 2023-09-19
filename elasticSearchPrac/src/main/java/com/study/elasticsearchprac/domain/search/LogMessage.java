package com.study.elasticsearchprac.domain.search;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Document(indexName = "logstash-2023.09.05-000001")
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogMessage {
    @Id @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text)
    private String message;
}
