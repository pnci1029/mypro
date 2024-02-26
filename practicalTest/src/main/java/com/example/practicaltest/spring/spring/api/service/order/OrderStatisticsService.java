package com.example.practicaltest.spring.spring.api.service.order;

import com.example.practicaltest.spring.spring.domain.order.Order;
import com.example.practicaltest.spring.spring.domain.order.OrderRepository;
import com.example.practicaltest.spring.spring.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderStatisticsService {
    private final OrderRepository orderRepository;

    public void sendOrderStatisticsMail(LocalDate localDate, String email) {
        // 해당 일자에 해당하는 결제를 조회하고
        List<Order> ordersBy = orderRepository.findOrdersBy(
                // localDate의 0시를 의미
                localDate.atStartOfDay(),
                localDate.plusDays(1).atStartOfDay(),
                OrderStatus.PAYMENT_COMPLETED
        );

        // 결제 조회된 금액들을 합산하여

        // 메일 발송

    }

}
