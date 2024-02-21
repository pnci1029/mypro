package com.example.practicaltest.spring.spring.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * select * from product where selling_type in ('')
     */
    List<Product> findAllBySellingTypeIn(List<ProductSellingType> sellingTypes);

    List<Product> findAllByProductNoIn(List<String> productNumbers);

    @Query(value = "select p.product_no from product as p order by  p.id desc limit 1", nativeQuery = true)
    String findLatestProduct();
}
