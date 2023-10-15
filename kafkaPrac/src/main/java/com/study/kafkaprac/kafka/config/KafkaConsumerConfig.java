//package com.study.kafkaprac.kafka.config;
//
//import com.study.kafkaprac.kafka.config.KafkaConsumerProperties;
//import com.study.kafkaprac.kafka.config.TaskExecutorConfig;
//import lombok.RequiredArgsConstructor;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.listener.ContainerProperties;
//import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import org.springframework.retry.backoff.FixedBackOffPolicy;
//import org.springframework.retry.policy.SimpleRetryPolicy;
//import org.springframework.retry.support.RetryTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableKafka
//@Configuration @RequiredArgsConstructor
//public class KafkaConsumerConfig {
//
//    @Autowired
//    private KafkaConsumerProperties kafkaConsumerProperties;
//    @Autowired
//    private TaskExecutorConfig taskExecutorConfig;
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaConsumerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//
//        factory.setConsumerFactory(consumerFactory());
//        factory.setConcurrency(3);
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
//
//        // 컨슈머 스레드 처리
//        factory.getContainerProperties().setConsumerTaskExecutor(taskExecutorConfig.executor());
//
//        // retry
//        factory.setRetryTemplate(retryTemplate());
//        factory.setRecoveryCallback(context -> {
//            System.out.println("consumer retry : " + context.toString());
//            return null;
//        });
//        factory.setErrorHandler(new SeekToCurrentErrorHandler());
//
//        return factory;
//    }
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        JsonDeserializer<String> deserializer = pushDeserializer();
//        return new DefaultKafkaConsumerFactory<>(
//                consumerFactoryConfig(deserializer),
//                new StringDeserializer(),
//                deserializer);
//    }
//
//    private Map<String, Object> consumerFactoryConfig(JsonDeserializer<String> deserializer) {
//        Map<String, Object> props = new HashMap<>();
//        props.put("bootstrap.servers", kafkaConsumerProperties.getBootstrapServers());
//        props.put("group.id", kafkaConsumerProperties.getGroupId());
//        props.put("key.deserializer", StringDeserializer.class.getName());
//        props.put("value.deserializer", deserializer);
//        props.put("enable.auto.commit", kafkaConsumerProperties.isEnableAutoCommit());
//        props.put("auto.offset.reset", kafkaConsumerProperties.getAutoOffsetReset());
//        props.put("max.poll.interval.ms", 5000);
//        return props;
//    }
//
//    private JsonDeserializer<String> pushDeserializer() {
//        JsonDeserializer<String> deserializer = new JsonDeserializer<>(String.class);
//        deserializer.setRemoveTypeHeaders(false);
//        deserializer.addTrustedPackages("*");
//        deserializer.setUseTypeMapperForKey(true);
//        return deserializer;
//    }
//
//    @Bean
//    RetryTemplate retryTemplate() {
//        RetryTemplate retryTemplate = new RetryTemplate();
//
//        // retry 정책
//        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
//        fixedBackOffPolicy.setBackOffPeriod(1000L);
//
//        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
//        retryPolicy.setMaxAttempts(3);
//
//        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
//        retryTemplate.setRetryPolicy(retryPolicy);
//
//        return retryTemplate;
//    }
//}
