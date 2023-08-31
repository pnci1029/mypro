package com.study.kafkaprac;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController @Slf4j
@RequestMapping("/kafka") @RequiredArgsConstructor
public class KafkaTopicController {

    private final KafkaAdmin kafkaAdmin;

    @GetMapping("/list-topics")
    public Set<String> listTopics() throws Exception {
        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());

        ListTopicsOptions options = new ListTopicsOptions();
        options.listInternal(true); // 내부 토픽도 표시할 경우

        ListTopicsResult listTopics = adminClient.listTopics(options);
        log.trace("list topcic");
        return listTopics.names().get();
    }

    @PostMapping("/create-topic")
    public String createTopic(@RequestBody Map<String, String> request) {
        String topicName = request.get("topicName");
        int partitions = Integer.parseInt(request.get("partitions"));
        int replicationFactor = Integer.parseInt(request.get("replicationFactor"));

        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());

        NewTopic newTopic = new NewTopic(topicName, partitions, (short) replicationFactor);
        log.trace("create topic");

        try {
            adminClient.createTopics(Collections.singletonList(newTopic)).all().get();
            return "Topic '" + topicName + "' created successfully.";
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return "Failed to create topic '" + topicName + "'. Error: " + e.getMessage();
        }
    }
}
