package com.example.pro.config;

import com.example.pro.dto.ChoiseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ValueOperations;

import java.util.UUID;

@SpringBootTest
public class RedisConfigTest {
    @Autowired
    RedisConfig redisConfig;

    @Test
    void redisBasic() {
        String key = UUID.randomUUID().toString().substring(0, 16);
        ValueOperations<String, Object> redis = redisConfig.redisTemplate().opsForValue();

        ChoiseDto dto = ChoiseDto.builder()
                .anger(10)
                .build();
        redis.set(key, dto);


    }
}
