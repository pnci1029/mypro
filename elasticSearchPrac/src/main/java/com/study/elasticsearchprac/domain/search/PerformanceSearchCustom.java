package com.study.elasticsearchprac.domain.search;

import java.util.List;

public interface PerformanceSearchCustom {
    void saveDocuments(PerformanceDocument performanceDocument);
    List<PerformanceDocument> searchByTitle(String title);
    List<PerformanceDocument> searchByDateTime(String place);
    List<PerformanceDocument> searchByPerformanceType(String type);
}
