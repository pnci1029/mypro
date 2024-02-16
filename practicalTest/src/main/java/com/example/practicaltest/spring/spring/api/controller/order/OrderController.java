package com.example.practicaltest.spring.spring.api.controller.order;

import com.example.practicaltest.spring.spring.api.controller.order.request.OrderCreateRequest;
import com.example.practicaltest.spring.spring.api.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public void createOrders(@RequestBody OrderCreateRequest orderCreateRequest) {
        LocalDateTime now = LocalDateTime.now();
        orderService.createdOrders(orderCreateRequest, now);
    }
}
