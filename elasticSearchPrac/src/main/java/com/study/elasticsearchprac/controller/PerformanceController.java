package com.study.elasticsearchprac.controller;

import com.study.elasticsearchprac.ResponseData;
import com.study.elasticsearchprac.domain.search.PerformanceDocument;
import com.study.elasticsearchprac.dto.CommonRequest;
import com.study.elasticsearchprac.dto.CommonResponse;
import com.study.elasticsearchprac.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PerformanceController {

    private final PerformanceService performanceService;
    @PostMapping("/performance")
    public ResponseEntity<CommonResponse> savePerformance(@RequestBody List<CommonRequest> commonRequest) {
        performanceService.savePerformance(commonRequest);
        return ResponseEntity.ok(CommonResponse.builder().message("엔티티 저장성공").build());
    }

    @PostMapping("/documents")
    public ResponseEntity<CommonResponse> saveDocuments(@RequestBody List<CommonRequest> commonRequest) {
        performanceService.saveDocuments(commonRequest);
        return ResponseEntity.ok(CommonResponse.builder().message("엘라스틱 저장성공").build());
    }

    @GetMapping("/title")
    public ResponseEntity<ResponseData<List<PerformanceDocument>>> searchByTitle(@RequestParam String title) {
        List<PerformanceDocument> documents = performanceService.searchByTitle(title);
        ResponseData response = new ResponseData("SUCCESS", HttpStatus.OK.value(), documents);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/place")
    public ResponseEntity<ResponseData<List<PerformanceDocument>>> searchByPlace(@RequestParam String place) {
        List<PerformanceDocument> documents = performanceService.searchByDateTime(place);
        ResponseData response = new ResponseData("SUCCESS", HttpStatus.OK.value(), documents);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/type")
    public ResponseEntity<ResponseData<List<PerformanceDocument>>> searchByPerformanceType(@RequestParam String performanceType) {
        List<PerformanceDocument> documents = performanceService.searchByPerformanceType(performanceType);
        ResponseData response = new ResponseData("SUCCESS", HttpStatus.OK.value(), documents);
        return ResponseEntity.ok().body(response);
    }
}
