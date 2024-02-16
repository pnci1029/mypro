package com.example.practicaltest.spring.spring.api.controller.order.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateRequest {
    private List<String> orderProductNumbers;

    @Builder
    public OrderCreateRequest(List<String> orderProductNumbers) {
        this.orderProductNumbers = orderProductNumbers;
    }
}
