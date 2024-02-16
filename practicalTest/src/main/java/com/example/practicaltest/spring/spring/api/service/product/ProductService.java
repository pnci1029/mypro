package com.example.practicaltest.spring.spring.api.service.product;

import com.example.practicaltest.spring.spring.api.service.product.response.ProductResponse;
import com.example.practicaltest.spring.spring.domain.product.Product;
import com.example.practicaltest.spring.spring.domain.product.ProductRepository;
import com.example.practicaltest.spring.spring.domain.product.ProductSellingType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService  {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts() {
        List<Product> productList = productRepository.findAllBySellingTypeIn(ProductSellingType.forDisplay());
        return productList.stream()
                .map(item -> ProductResponse.of(item))
                .collect(Collectors.toList());
    }
}
