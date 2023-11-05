package com.study.elasticsearchprac.domain.test;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface LogMessageRepository extends ElasticsearchRepository<LogMessage, String> {
    List<LogMessage> findByMessage(String message);

}
