package com.example.practicaltest.spring.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityListeners;

@SpringBootApplication
public class PracticalTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticalTestApplication.class, args);
    }

}
