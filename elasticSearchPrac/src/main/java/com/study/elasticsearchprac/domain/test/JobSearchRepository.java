package com.study.elasticsearchprac.domain.test;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface JobSearchRepository extends ElasticsearchRepository<Job, Long>, JobSearchRepositoryCustom {
}
