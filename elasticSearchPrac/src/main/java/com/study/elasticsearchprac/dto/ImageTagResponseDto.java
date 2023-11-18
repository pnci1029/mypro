package com.study.elasticsearchprac.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
public class ImageTagResponseDto {
    private String imageName;
    private List<String> imageTag;
}
