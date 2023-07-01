package com.study.elasticsearchprac.service;

import com.study.elasticsearchprac.domain.search.Job;
import com.study.elasticsearchprac.domain.search.JobSearchRepository;
import com.study.elasticsearchprac.dto.MemberJobResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor @Service @Slf4j
public class JobSearchService {
    private final JobSearchRepository jobSearchRepository;
    public void saveJob(Job job) {
        Job jobs = jobSearchRepository.save(job);
        log.info("id : {}",jobs.getId());
        log.info("name : {}",jobs.getName());
    }

    public MemberJobResponseDto getJobAndMember(String value) {
        return jobSearchRepository.searchMemberAndJobByValue(value);
    }
}
