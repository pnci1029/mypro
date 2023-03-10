package com.example.pro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder @Getter @NoArgsConstructor @AllArgsConstructor
public class ChoiseDto {
    private int stress;
    private int anger;
    private int tired;
    private String mealTime;
    private int healthful;
    private String lastMeal;
    private String dislike;
}
