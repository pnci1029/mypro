package com.example.pro.pracPackage.elasticSearch;

import io.micrometer.core.lang.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface ElasticsearchRepository<T, Id> extends PagingAndSortingRepository<T, Id> {

    Page<T> searchSimilar(T entity, @Nullable String[] fields, Pageable pageable);
}
