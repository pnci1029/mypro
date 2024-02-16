package com.example.practicaltest.spring.spring.api.service.order;

import com.example.practicaltest.spring.spring.api.controller.order.request.OrderCreateRequest;
import com.example.practicaltest.spring.spring.api.service.order.response.OrderCreateResponse;
import com.example.practicaltest.spring.spring.domain.order.Order;
import com.example.practicaltest.spring.spring.domain.order.OrderRepository;
import com.example.practicaltest.spring.spring.domain.order.OrderStatus;
import com.example.practicaltest.spring.spring.domain.product.Product;
import com.example.practicaltest.spring.spring.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderCreateResponse createdOrders(OrderCreateRequest orderCreateRequest, LocalDateTime registeredDateTime) {
        List<String> orderCreate = orderCreateRequest.getOrderProductNumbers();
        List<Product> targetProducts = productRepository.findAllByProductNoIn(orderCreate);

        Order order = Order.create(targetProducts, registeredDateTime);

        Order savedOrder = orderRepository.save(order);
        return OrderCreateResponse.of(savedOrder);

//        final int[] totalPrice = {0};
//        targetProducts.forEach(product -> totalPrice[0] += product.getPrice());
//
//        orderRepository.save(
//                Order.builder()
//                        .totalPrice(totalPrice[0])
//                        .orderStatus(OrderStatus.INIT)
//                        .registeredDateTime(registeredDateTime)
//                        .build());
    }
}
