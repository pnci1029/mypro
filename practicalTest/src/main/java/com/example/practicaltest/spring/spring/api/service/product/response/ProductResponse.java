package com.example.practicaltest.spring.spring.api.service.product.response;

import com.example.practicaltest.spring.spring.domain.product.Product;
import com.example.practicaltest.spring.spring.domain.product.ProductSellingType;
import com.example.practicaltest.spring.spring.domain.product.ProductType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponse {

    private long id;
    private String productNo;
    private ProductType productType;
    private ProductSellingType sellingType;
    private String name;
    private int price;

    @Builder
    private ProductResponse(long id, String productNo, ProductType productType, ProductSellingType sellingType, String name, int price) {
        this.id = id;
        this.productNo = productNo;
        this.productType = productType;
        this.sellingType = sellingType;
        this.name = name;
        this.price = price;
    }

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .sellingType(product.getSellingType())
                .productType(product.getProductType())
                .productNo(product.getProductNo())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
