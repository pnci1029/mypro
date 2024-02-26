package com.example.practicaltest.spring.spring.api.controller.product.dto.request;

import com.example.practicaltest.spring.spring.api.service.product.request.ProductCreateServiceRequest;
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
public class ProductCreateRequest {

    @NotNull(message = "상품 타입은 필수입니다.")
    private ProductType productType;
    @NotNull(message = "상품 판매 타입은 필수입니다.")
    private ProductSellingType sellingType;
    @NotBlank(message = "상품 이름은 필수입니다.")
    private String name;
    @Positive(message = "상품 가격은 양수여야 합니다.")
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

    public ProductCreateServiceRequest toService() {
        return ProductCreateServiceRequest
                .builder()
                .name(name)
                .price(price)
                .productType(productType)
                .sellingType(sellingType)
                .build();
    }
}
