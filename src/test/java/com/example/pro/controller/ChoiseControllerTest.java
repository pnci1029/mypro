package com.example.pro.controller;

import com.example.pro.domain.ChoiseRepository;
import com.example.pro.domain.entity.Choise;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ChoiseControllerTest {

    @Autowired
    ChoiseRepository choiseRepository;

    @Test
    void choiseSequenceTest() {
        Choise target = Choise.builder()
                .anger(10)
                .tired(20)
                .stress(50)
                .dislike("Vegi")
                .healthful(100)
                .mealTime("Lunch")
                .lastMeal("Steak")
                .build();
        Choise data = choiseRepository.save(target);
        assertThat(target.getId()).isEqualTo(1L);
        assertThat(data.getParticipateCount()).isEqualTo(1L);
    }

}
