package com.study.elasticsearchprac.domain.search;

import com.study.elasticsearchprac.domain.PerformanceType;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "performance")
@Mapping(mappingPath = "elastic/performance-mapping.json")
@Setting(settingPath = "elastic/performance-setting.json") @Builder @AllArgsConstructor
public class PerformanceDocument {
    @Id
    @Field(type = FieldType.Keyword)
    private Long id;

    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String dateTime;
    @Field(type = FieldType.Text)
    private String length;
    @Field(type = FieldType.Text)
    private String imageLink;
    @Field(type = FieldType.Text)
    private PerformanceType performanceType;
}
