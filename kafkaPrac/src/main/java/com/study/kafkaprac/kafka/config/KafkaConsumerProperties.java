//package com.study.kafkaprac.kafka.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//@Component
//@ConfigurationProperties(prefix = "kafka.consumer")
//public class KafkaConsumerProperties {
//    private String bootstrapServers;
//    private String groupId;
//    private boolean enableAutoCommit;
//    private String autoOffsetReset;
//
//    // Getter and Setter methods
//
//    public String getBootstrapServers() {
//        return bootstrapServers;
//    }
//
//    public void setBootstrapServers(String bootstrapServers) {
//        this.bootstrapServers = bootstrapServers;
//    }
//
//    public String getGroupId() {
//        return groupId;
//    }
//
//    public void setGroupId(String groupId) {
//        this.groupId = groupId;
//    }
//
//    public boolean isEnableAutoCommit() {
//        return enableAutoCommit;
//    }
//
//    public void setEnableAutoCommit(boolean enableAutoCommit) {
//        this.enableAutoCommit = enableAutoCommit;
//    }
//
//    public String getAutoOffsetReset() {
//        return autoOffsetReset;
//    }
//
//    public void setAutoOffsetReset(String autoOffsetReset) {
//        this.autoOffsetReset = autoOffsetReset;
//    }
//}
//
