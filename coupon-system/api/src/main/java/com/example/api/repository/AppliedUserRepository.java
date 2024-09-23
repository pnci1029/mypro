package com.example.api.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * 1인 1개 쿠폰 케이스에 유저 정보를 set으로 담을 레디스 레포
 */
@Repository @RequiredArgsConstructor
public class AppliedUserRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public Long add(Long userId) {
        return redisTemplate.opsForSet()
                .add("applied_user", userId.toString());
    }
}
