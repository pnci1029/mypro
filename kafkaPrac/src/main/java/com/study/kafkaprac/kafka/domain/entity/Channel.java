package com.study.kafkaprac.kafka.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Builder
@Getter @NoArgsConstructor @AllArgsConstructor
public class Channel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String topicName;
    private String message;

}
