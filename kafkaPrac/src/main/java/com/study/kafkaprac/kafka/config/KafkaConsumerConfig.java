//package com.study.kafkaprac.kafka.config;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
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
//@Configuration
//public class KafkaConsumerConfig {
//
//    @Value("${setting.kafka-server}")
//    private String bootstrapServers;
//
//    @Value("${setting.group}")
//    private String groupId;
//
//    @Value("${setting.commit}")
//    private boolean autocommit;
//
//    @Value("${setting.earliest}")
//    private String earliest;
//
//    @Autowired
//    private TaskExecutorConfig taskExecutorConfig;
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaConsumerFactory(){
//        ConcurrentKafkaListenerContainerFactory<String, String> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//
//        // 기본 팩토리 설정
//        factory.setConsumerFactory(consumerFactory());
//        factory.setConcurrency(3); // 하나의 리스너에서 스레드 3개로 처리.
//
//        // 수동 커밋
//        // 리스너에서 acknowledgment가 호출될 때 마다, 커밋
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
//    public ConsumerFactory<Object, Object> consumerFactory(){
//        JsonDeserializer deserializer = pushDeserializer();
//        return new DefaultKafkaConsumerFactory<>(
//                consumerFactoryConfig(deserializer),
//                new StringDeserializer(),
//                deserializer);
//    }
//
//    private Map<Object, Object> consumerFactoryConfig(JsonDeserializer deserializer) {
//        HashMap<Object, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, this.groupId);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, this.autocommit);
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, this.earliest);
//        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 5000); // poll 요청을 보내고, 다음 poll 요청을 보내는데 까지의 최대 시간 설정
//        return props;
//    }
//
//    private JsonDeserializer pushDeserializer() {
//        JsonDeserializer deserializer = new JsonDeserializer<>(String.class);
//        deserializer.setRemoveTypeHeaders(false);
//        deserializer.addTrustedPackages("*");
//        deserializer.setUseTypeMapperForKey(true);
//        return deserializer;
//    }
//
//    // retry관련 내용 블로그
//    // <https://wfreud.tistory.com/352>
//    // <https://objectpartners.com/2018/11/21/building-resilient-kafka-consumers-with-spring-retry/>
//    // <https://blog.leocat.kr/notes/2018/10/10/translation-retrying-consumer-architecture-in-the-apache-kafka>
//    // <https://gunju-ko.github.io/kafka/spring-kafka/2018/04/16/Spring-Kafka-Retry.html>
//    @Bean
//    RetryTemplate retryTemplate(){
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