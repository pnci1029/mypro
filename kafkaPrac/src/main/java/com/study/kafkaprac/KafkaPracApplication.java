package com.study.kafkaprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.study.kafkaprac")
public class KafkaPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaPracApplication.class, args);
    }

}
