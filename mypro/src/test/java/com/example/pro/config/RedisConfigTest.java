package com.example.pro.config;

import com.example.pro.dto.ChoiseDto;
import com.example.pro.dto.ChoiseDtoTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ValueOperations;

import java.util.UUID;

@SpringBootTest
public class RedisConfigTest {
    @Autowired
    RedisConfig redisConfig;
    private static final String key = UUID.randomUUID().toString().substring(0, 16);

    @Test
    void redisBasic() {
        ValueOperations<String, Object> redis = redisConfig.redisTemplate().opsForValue();

        ChoiseDto dto = ChoiseDto.builder()
                .anger(10)
                .build();
        redis.set(key, dto);
    }

    @Test
    void redisStep2() {
        ValueOperations<String, Object> redis = redisConfig.redisTemplate().opsForValue();
        Object o = redis.get(key);
        System.out.println("o = " + o);
        ChoiseDto dto = ChoiseDto.builder()
                .tired(20)
                .build();
        redis.set(key,dto);
    }

    @Test
    void redisStep3() {
        ValueOperations<String, Object> redis = redisConfig.redisTemplate().opsForValue();
        ObjectMapper om = new ObjectMapper();
        Object o = redis.get(key);
        ChoiseDto result = om.convertValue(o, ChoiseDto.class);
        Assertions.assertThat(result.getTired()).isEqualTo(20);

    }
}
