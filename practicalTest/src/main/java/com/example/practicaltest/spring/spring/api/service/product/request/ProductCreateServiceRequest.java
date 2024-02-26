package com.example.practicaltest.spring.spring.api.service.product.request;

import com.example.practicaltest.spring.spring.domain.product.Product;
import com.example.practicaltest.spring.spring.domain.product.ProductSellingType;
import com.example.practicaltest.spring.spring.domain.product.ProductType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class ProductCreateServiceRequest {

    private ProductType productType;
    private ProductSellingType sellingType;
    private String name;
    private int price;

    @Builder
    private ProductCreateServiceRequest(ProductType productType, ProductSellingType sellingType, String name, int price) {
        this.productType = productType;
        this.sellingType = sellingType;
        this.name = name;
        this.price = price;
    }

    public static ProductCreateServiceRequest of(ProductType productType, ProductSellingType sellingType, String name, int price) {
        return ProductCreateServiceRequest.builder()
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
