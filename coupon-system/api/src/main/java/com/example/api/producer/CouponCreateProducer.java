package com.example.api.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CouponCreateProducer {
    public CouponCreateProducer(KafkaTemplate<String, Long> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private final KafkaTemplate<String, Long> kafkaTemplate;

    public void create(Long userId) {
        kafkaTemplate.send("coupon_create", userId);
    }
}
