package com.example.pro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class MemberGenderRequestDto {
    private String name;
    private String gender;

    @Entity
    @Builder @NoArgsConstructor @AllArgsConstructor @Getter
    public static class MemberGender {
        @Id
        private Long id;
        private String gender;
        private String name;
    }
}
