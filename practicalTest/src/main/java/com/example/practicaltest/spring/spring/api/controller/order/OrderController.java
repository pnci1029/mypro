package com.example.practicaltest.spring.spring.api.controller.order;

import com.example.practicaltest.spring.spring.api.ApiResponse;
import com.example.practicaltest.spring.spring.api.controller.order.request.OrderCreateRequest;
import com.example.practicaltest.spring.spring.api.service.order.OrderService;
import com.example.practicaltest.spring.spring.api.service.order.response.OrderCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public ApiResponse<OrderCreateResponse> createOrders(@Valid @RequestBody OrderCreateRequest orderCreateRequest) {
        LocalDateTime now = LocalDateTime.now();
        return ApiResponse.ok(orderService.createdOrders(orderCreateRequest, now));
    }
}
