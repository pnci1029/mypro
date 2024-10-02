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

    @DisplayName("V4.1명당 1개의 쿠폰을 발급하고 1000개의 요청을 받았을때 100개까지 쿠폰을 등록한다.")
    @Test
    void checkWhenApplyMultipleRequestV4() throws InterruptedException {
        // given
        int threadCount = 1000;

        // 멀티스레드 병렬작업을 도와주는 자바 API
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        // 다른 스레드에서 수행하는 작업을 기다리도록 처리하는 클래스
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        // when
        for(int i = 0; i < threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    applyService.applyV4(1L);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        Thread.sleep(2000);
        long count = couponRepository.count();

        // then
        assertThat(count).isEqualTo(1);
    }


    @DisplayName("V3.1000개의 요청을 받았을때 100개까지 쿠폰을 등록한다.")
    @Test
    void checkWhenApplyMultipleRequestV3() throws InterruptedException {
        // given
        int threadCount = 1000;

        // 멀티스레드 병렬작업을 도와주는 자바 API
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        // 다른 스레드에서 수행하는 작업을 기다리도록 처리하는 클래스
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        // when
        for(int i = 0; i < threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    applyService.applyV3(userId);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        Thread.sleep(2000);
        long count = couponRepository.count();


        // then
        assertThat(count).isEqualTo(100);
    }

    @DisplayName("V2.1000개의 요청을 받았을때 100개까지 쿠폰을 등록한다.")
    @Test
    void checkWhenApplyMultipleRequestV2() throws InterruptedException {
        // given
        int threadCount = 1000;

        // 멀티스레드 병렬작업을 도와주는 자바 API
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        // 다른 스레드에서 수행하는 작업을 기다리도록 처리하는 클래스
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        // when
        for(int i = 0; i < threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    applyService.applyV2(userId);
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

    @DisplayName("V1.1000개의 요청을 받았을때 100개까지 쿠폰을 등록한다.")
    @Test
    void checkWhenApplyMultipleRequestV1() throws InterruptedException {
        // given
        int threadCount = 1000;

        // 멀티스레드 병렬작업을 도와주는 자바 API
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        // 다른 스레드에서 수행하는 작업을 기다리도록 처리하는 클래스
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        // when
        for(int i = 0; i < threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    applyService.applyV1(userId);
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