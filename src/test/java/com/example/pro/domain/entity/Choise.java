package com.example.pro.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor @AllArgsConstructor
@SequenceGenerator(
        name = "CHOISE_SEQ_GENERATOR",
        sequenceName = "CHOISE_SEQ",
        initialValue = 1,
        allocationSize = 50
)
public class Choise {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHOISE_SEQ_GENERATOR")
    private Long participateCount;
    private int stress;
    private int anger;
    private int tired;
    private String mealTime;
    private int healthful;
    private String lastMeal;
    private String dislike;

    @Builder
    public Choise(int stress, int anger, int tired, String mealTime, int healthful, String lastMeal, String dislike) {
        this.stress = stress;
        this.anger = anger;
        this.tired = tired;
        this.mealTime = mealTime;
        this.healthful = healthful;
        this.lastMeal = lastMeal;
        this.dislike = dislike;
    }

}
