package com.example.pro;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
@EnableBatchProcessing
@SpringBootTest
class ProApplicationTests implements CommandLineRunner {

    @Test
    void contextLoads() {
    }

    @Override
    public void run(String... args) throws Exception {
        JobParameters jobParameters;
    }
}
