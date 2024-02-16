package com.example.practicaltest.spring.spring.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * select * from product where selling_type in ('')
     */
    List<Product> findAllBySellingTypeIn(List<ProductSellingType> sellingTypes);
}
