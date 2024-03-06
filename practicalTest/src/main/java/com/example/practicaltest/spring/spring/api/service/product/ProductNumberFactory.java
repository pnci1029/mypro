package com.example.practicaltest.spring.spring.api.service.product;

import com.example.practicaltest.spring.spring.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor
public class ProductNumberFactory {

    private final ProductRepository productRepository;

    public String createNewProductNo() {
        String latestProductNo = productRepository.findLatestProduct();
        if (latestProductNo == null) {
            return "001";
        }
        return String.format("%03d", Integer.parseInt(latestProductNo) + 1);
    }
}
