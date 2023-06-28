package com.study.elasticsearchprac.service;

import com.study.elasticsearchprac.domain.Performance;
import com.study.elasticsearchprac.domain.search.PerformanceDocument;
import com.study.elasticsearchprac.domain.PerformanceRepository;
import com.study.elasticsearchprac.domain.search.PerformanceSearchCustom;
import com.study.elasticsearchprac.dto.CommonRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @Slf4j @RequiredArgsConstructor
public class PerformanceService {
    private final PerformanceRepository performanceRepository;
    private final PerformanceSearchCustom performanceSearchCustom;
    public void savePerformance(List<CommonRequest> commonRequest) {
        List<Performance> performances = commonRequest.stream()
                .map(request -> Performance.builder()
                        .title(request.getTitle())
                        .performanceType(request.getPerformanceType())
                        .dateTime(request.getDateTime())
                        .length(request.getLength())
                        .imageLink(request.getImageLink())
                        .build())
                .collect(Collectors.toList());

        performanceRepository.saveAll(performances);
    }

    public void saveDocuments(List<CommonRequest> commonRequest) {
        for (CommonRequest request : commonRequest) {
            PerformanceDocument performance = PerformanceDocument.builder()
                    .title(request.getTitle())
                    .performanceType(request.getPerformanceType())
                    .dateTime(request.getDateTime())
                    .length(request.getLength())
                    .imageLink(request.getImageLink())
                    .build();
            performanceSearchCustom.saveDocuments(performance);
        }
    }

    public List<PerformanceDocument> searchByTitle(String title) {
        return performanceSearchCustom.searchByTitle(title);
    }

    public List<PerformanceDocument> searchByDateTime(String dateTime) {
        return performanceSearchCustom.searchByDateTime(dateTime);
    }

    public List<PerformanceDocument> searchByPerformanceType(String performanceType) {
        return performanceSearchCustom.searchByPerformanceType(performanceType);
    }
}
