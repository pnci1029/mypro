package com.example.practicaltest.spring.spring.api.controller.order.request;

import com.example.practicaltest.spring.spring.api.service.order.request.OrderCreateServiceRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    @NotEmpty(message = "상품 번호 리스트는 필수입니다.")
    private List<String> orderProductNumbers = new ArrayList<>();

    @Builder
    public OrderCreateRequest(List<String> orderProductNumbers) {
        this.orderProductNumbers = orderProductNumbers;
    }

    public OrderCreateServiceRequest toService() {
        return OrderCreateServiceRequest
                .builder()
                .orderProductNumbers(orderProductNumbers)
                .build();
    }

}
