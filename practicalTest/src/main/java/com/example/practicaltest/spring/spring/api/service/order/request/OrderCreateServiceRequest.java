package com.example.practicaltest.spring.spring.api.service.order.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderCreateServiceRequest {
    private List<String> orderProductNumbers = new ArrayList<>();

    @Builder
    public OrderCreateServiceRequest(List<String> orderProductNumbers) {
        this.orderProductNumbers = orderProductNumbers;
    }
}
