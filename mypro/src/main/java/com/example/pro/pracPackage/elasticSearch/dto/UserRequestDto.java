package com.example.pro.pracPackage.elasticSearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor
public class UserRequestDto {
    private String name;
    private String description;
}
