package com.example.practicaltest.spring.spring.api.service.order.response;

import com.example.practicaltest.spring.spring.api.service.product.response.ProductResponse;
import com.example.practicaltest.spring.spring.domain.order.Order;
import com.example.practicaltest.spring.spring.domain.order.OrderStatus;
import com.example.practicaltest.spring.spring.domain.orderproduct.OrderProduct;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderCreateResponse {

    private Long id;

    private int totalPrice;
    private LocalDateTime registeredDateTime;

    private List<ProductResponse> productResponses;

    @Builder
    private OrderCreateResponse(Long id, int totalPrice, LocalDateTime registeredDateTime, List<ProductResponse> productResponses) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.registeredDateTime = registeredDateTime;
        this.productResponses = productResponses;
    }

    public static OrderCreateResponse of(Order order) {
        return OrderCreateResponse.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .registeredDateTime(order.getRegisteredDateTime())
                .productResponses(
                        order.getOrderProduct().stream()
                                .map(orderProduct -> ProductResponse.of(orderProduct.getProduct()))
                                .collect(Collectors.toList())
                )
                .build();

    }
}
