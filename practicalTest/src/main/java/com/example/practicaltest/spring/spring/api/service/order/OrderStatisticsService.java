package com.example.practicaltest.spring.spring.api.service.order;

import com.example.practicaltest.spring.spring.api.service.mail.MailService;
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
    private final MailService mailService;

    public void sendOrderStatisticsMail(LocalDate localDate, String email) {
        // 해당 일자에 해당하는 결제를 조회하고
        List<Order> ordersBy = orderRepository.findOrdersBy(
                // localDate의 0시를 의미
                localDate.atStartOfDay(),
                localDate.plusDays(1).atStartOfDay(),
                OrderStatus.PAYMENT_COMPLETED
        );

        // 결제 조회된 금액들을 합산하여
        int totalAmount = ordersBy.stream()
                .mapToInt(Order::getTotalPrice)
                .sum();

        // 메일 발송
        boolean result = mailService.sendMail("no-reply@cafekiosk.com",
                email,
                String.format("[매출통계] %s", localDate),
                String.format("총 매출 합계는 %s원입니다.", totalAmount));

        if (!result) {
            throw new IllegalArgumentException("매출 통계 메일 전송에 실패했습니다.");
        }

    }

}
