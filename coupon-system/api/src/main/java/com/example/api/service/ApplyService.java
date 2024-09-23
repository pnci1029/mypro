package com.example.api.service;

import com.example.api.domain.Coupon;
import com.example.api.producer.CouponCreateProducer;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;

    public ApplyService(CouponRepository couponRepository,
                        CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
    }

    public void applyV4(Long userId) {
        Long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }

    /**
     * 카프카 + 레디스
     */
    public void applyV3(Long userId) {
        Long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }

    /**
     * 레디스
     */
    public void applyV2(Long userId) {
        Long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

        couponRepository.save(new Coupon(userId));
    }

    /**
     * 순수 DB
     */
    public void applyV1(Long userId) {
        long count = couponRepository.count();

        if (count > 100) {
            return;
        }

        couponRepository.save(new Coupon(userId));
    }
}
