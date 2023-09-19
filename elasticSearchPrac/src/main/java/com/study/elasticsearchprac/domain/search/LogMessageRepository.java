package com.study.elasticsearchprac.domain.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface LogMessageRepository extends ElasticsearchRepository<LogMessage, String> {
    List<LogMessage> findByMessage(String message);

}
