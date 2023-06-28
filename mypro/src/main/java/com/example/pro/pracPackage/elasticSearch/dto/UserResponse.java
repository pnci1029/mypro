package com.example.pro.pracPackage.elasticSearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String description;
    public static UserResponse from(UserResponseDto userResponseDto) {
        return new UserResponse(
                userResponseDto.getId(),
                userResponseDto.getName(),
                userResponseDto.getDescription()
        );
    }

}