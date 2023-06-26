package com.example.pro.pracPackage.elasticSearch.dto;

import com.example.pro.pracPackage.elasticSearch.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String description;
    public static UserResponseDto from(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getDescription());
    }
}

