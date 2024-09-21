package com.example.consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CouponCreatedConsumer {

    @KafkaListener(groupId = "group_1", topics = "coupon_create")
    public void listener(Long userId) {
        System.out.println(userId);

    }
}
