package com.study.elasticsearchprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(
//        type = FilterType.ASSIGNABLE_TYPE,
//        classes = PerformanceSearchRepositoryRepository.class))
@SpringBootApplication
public class ElasticSearchPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchPracApplication.class, args);
    }

}
