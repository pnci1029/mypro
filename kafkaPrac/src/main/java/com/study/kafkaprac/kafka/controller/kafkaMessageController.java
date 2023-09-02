package com.study.kafkaprac.kafka.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class kafkaMessageController {
    private final KafkaConsumerController kafkaConsumerController;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/message")
    public void consumesMessage(@RequestParam(name = "topic") String topic,
                                @RequestParam(name = "message") String message) {

        kafkaTemplate.send(topic, message);
        log.info("topic : {}, message : {}", topic, message);
    }
}
