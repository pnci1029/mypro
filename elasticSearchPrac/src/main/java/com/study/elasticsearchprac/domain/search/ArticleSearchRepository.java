package com.study.elasticsearchprac.domain.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleSearchRepository extends ElasticsearchRepository<ArticleSearch, Long> {
}
