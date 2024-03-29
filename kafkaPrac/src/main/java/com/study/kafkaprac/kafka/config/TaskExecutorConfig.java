//package com.study.kafkaprac.kafka.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//@Configuration
//public class TaskExecutorConfig {
//
//    @Bean
//    public ThreadPoolTaskExecutor executor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(10);
//        executor.setMaxPoolSize(200);
//        executor.setQueueCapacity(250);
//        executor.setThreadFactory(new CustomizableThreadFactory("kafka-thread")); // 이름 prefix
//        return executor;
//    }
//}
