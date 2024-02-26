package com.example.practicaltest.spring.spring.api.controller.product.dto.request;

import com.example.practicaltest.spring.spring.domain.product.Product;
import com.example.practicaltest.spring.spring.domain.product.ProductSellingType;
import com.example.practicaltest.spring.spring.domain.product.ProductType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {

    private ProductType productType;
    private ProductSellingType sellingType;
    private String name;
    private int price;

    @Builder
    private ProductCreateRequest(ProductType productType, ProductSellingType sellingType, String name, int price) {
        this.productType = productType;
        this.sellingType = sellingType;
        this.name = name;
        this.price = price;
    }

    public static ProductCreateRequest of(ProductType productType, ProductSellingType sellingType, String name, int price) {
        return ProductCreateRequest.builder()
                .name(name)
                .price(price)
                .productType(productType)
                .sellingType(sellingType)
                .build();
    }

    public Product toEntity(String nextProductNo) {
        return Product.builder()
                .name(this.name)
                .price(this.price)
                .sellingType(this.sellingType)
                .productType(this.productType)
                .productNo(nextProductNo)
                .build();
    }

}
