package com.example.api.service;

import com.example.api.repository.CouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplyServiceTest {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private CouponRepository couponRepository;

    @DisplayName("쿠폰 응모를 첫번째로 한 경우 정상 발급이 되어야한다.")
    @Test
    void checkWhenApplyFirstTime() {
        // given
        applyService.apply(1L);

        // when
        long count = couponRepository.count();

        // then
        assertThat(count).isEqualTo(1L);
    }

}