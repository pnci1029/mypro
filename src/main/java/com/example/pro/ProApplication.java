package com.example.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProApplication.class, args);
    }

    /**
     * 스프링 액츄에이터 httpExchange 구현체 빈 등록
     */
    @Bean
    public InMemoryHttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }
}
