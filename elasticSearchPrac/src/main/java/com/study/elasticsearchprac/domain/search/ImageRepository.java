package com.study.elasticsearchprac.domain.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ImageRepository extends ElasticsearchRepository<Image, Long> {
}
