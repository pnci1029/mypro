package com.example.pro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder @Getter @NoArgsConstructor @AllArgsConstructor
public class ChoiseDto {
    @NotNull @Min(0) @Max(100)
    private int stress;
    @NotNull @Min(0) @Max(100)
    private int anger;
    @NotNull @Min(0) @Max(100)
    private int tired;
    @NotNull @Min(0) @Max(100)
    private int healthful;
    private String mealTime;
    private String lastMeal;
    private String dislike;
}
