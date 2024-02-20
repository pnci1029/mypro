package com.example.practicaltest.spring.spring.domain.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findAllByProductNoIn(List<String> productNo);
}
