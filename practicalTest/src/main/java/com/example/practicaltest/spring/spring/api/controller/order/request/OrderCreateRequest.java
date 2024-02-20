package com.example.practicaltest.spring.spring.api.controller.order.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {
    private List<String> orderProductNumbers = new ArrayList<>();

    @Builder
    public OrderCreateRequest(List<String> orderProductNumbers) {
        this.orderProductNumbers = orderProductNumbers;
    }
}
