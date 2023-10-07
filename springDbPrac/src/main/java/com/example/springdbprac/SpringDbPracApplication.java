package com.example.springdbprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringDbPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDbPracApplication.class, args);
    }

}
