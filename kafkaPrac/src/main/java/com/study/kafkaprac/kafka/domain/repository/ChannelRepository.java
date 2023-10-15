package com.study.kafkaprac.kafka.domain.repository;

import com.study.kafkaprac.kafka.domain.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
