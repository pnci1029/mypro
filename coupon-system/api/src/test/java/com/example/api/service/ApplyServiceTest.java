package com.example.api.service;

import com.example.api.repository.CouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        applyService.applyV2(1L);

        // when
        long count = couponRepository.count();

        // then
        assertThat(count).isEqualTo(1L);
    }


    @DisplayName("1000개의 요청을 받았을때 100개까지 쿠폰을 등록한다.")
    @Test
    void checkWhenApplyMultipleRequest() throws InterruptedException {
        // given
        int threadCount = 1000;

        // 멀티스레드 병렬작업을 도와주는 자바 API
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        // 다른 스레드에서 수행하는 작업을 기다리도록 처리하는 클래스
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        // when
        for(int i = 0; i < threadCount; i++) {
            int userId = i;
            executorService.submit(() -> {
                try {
                    applyService.applyV2((long) userId);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        long count = couponRepository.count();

        // then
        assertThat(count).isEqualTo(100);
    }
}