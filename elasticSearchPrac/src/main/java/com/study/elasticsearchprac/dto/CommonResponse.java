package com.study.elasticsearchprac.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class CommonResponse {
    private String message;
}
