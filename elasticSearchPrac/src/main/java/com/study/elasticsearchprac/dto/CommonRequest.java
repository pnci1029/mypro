package com.study.elasticsearchprac.dto;

import com.study.elasticsearchprac.domain.test.PerformanceType;
import lombok.Data;

@Data
public class CommonRequest {
    private String title;
    private String dateTime;
    private String length;
    private String imageLink;
    private PerformanceType performanceType;
}
