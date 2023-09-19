package com.study.elasticsearchprac.service;

import com.study.elasticsearchprac.domain.search.LogMessage;
import com.study.elasticsearchprac.domain.search.LogMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class LogMessageService {
    private final LogMessageRepository logMessageRepository;

    public List<LogMessage> searchMessage(String keyword) {
        return logMessageRepository.findByMessage(keyword);
    }
}
