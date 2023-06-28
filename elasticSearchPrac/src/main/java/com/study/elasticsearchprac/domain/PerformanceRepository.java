package com.study.elasticsearchprac.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}
