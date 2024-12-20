package com.example.consumer.consumer;

import com.example.consumer.domain.Coupon;
import com.example.consumer.domain.FailedEvent;
import com.example.consumer.repository.CouponRepository;
import com.example.consumer.repository.FailedEventRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component @RequiredArgsConstructor
public class CouponCreatedConsumer {

    private final CouponRepository couponRepository;
    private final FailedEventRepository failedEventRepository;
    private final Logger logger = LoggerFactory.getLogger(CouponCreatedConsumer.class);

    @KafkaListener(groupId = "group_1", topics = "coupon_create")
    public void listener(Long userId) {
        try {
            System.out.println(userId);
            couponRepository.save(new Coupon(userId));
        } catch (Exception e) {
            logger.error("failed event : "+e.getMessage());
            failedEventRepository.save(new FailedEvent(userId));
        }

    }
}
