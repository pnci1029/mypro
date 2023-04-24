package com.example.pro.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

public class ChoiseDtoTest {

    @DisplayName("dto 밸리데이션 체크")
    @Test
    public void notNullTest() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

        ChoiseDto dto = ChoiseDto.builder()
                .anger(500)
                .build();

        Assertions.assertThrows(MethodArgumentNotValidException.class,
                () -> {
                    validatorFactory.getValidator().validate(dto);
                });
    }

}
