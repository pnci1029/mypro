package com.study.elasticsearchprac.domain.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PerformanceSearchRepository extends ElasticsearchRepository<PerformanceDocument, Long>, PerformanceSearchCustom {
}
