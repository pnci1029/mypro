package com.study.elasticsearchprac.controller;

import com.study.elasticsearchprac.domain.test.Job;
import com.study.elasticsearchprac.dto.MemberJobResponseDto;
import com.study.elasticsearchprac.service.JobSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController @RequiredArgsConstructor @Slf4j
@RequestMapping("/job")
public class JobController {
    private final JobSearchService jobSearchService;

    @PostMapping("/insert")
    public void insertJob(@RequestBody Job job) {
        jobSearchService.saveJob(job);
    }

    @GetMapping("/get")
    public MemberJobResponseDto getJobAndMember(@RequestParam(name = "value") String value) {
        return jobSearchService.getJobAndMember(value);
    }
}
