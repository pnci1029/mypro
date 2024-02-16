package com.example.practicaltest.spring.spring.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ProductSellingType {

    SELLING("판매 중"),
    HOLD("보류"),
    STOP_SELLING("판매 종료");

    private final String text;

    public static List<ProductSellingType> forDisplay() {
        return List.of(SELLING, HOLD);
    }
}
