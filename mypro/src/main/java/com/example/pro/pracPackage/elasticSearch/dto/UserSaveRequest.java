package com.example.pro.pracPackage.elasticSearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserSaveRequest {
    private String name;
    private String nickName;
    private int age;
    private String description;
}
