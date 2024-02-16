package com.example.practicaltest.spring.spring.domain.product;

import com.example.practicaltest.spring.spring.api.service.product.response.ProductResponse;
import com.example.practicaltest.spring.spring.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String productNo;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    private ProductSellingType sellingType;

    private String name;
    private int price;

    @Builder
    private Product(String productNo, ProductType productType, ProductSellingType sellingType, String name, int price) {
        this.productNo = productNo;
        this.productType = productType;
        this.sellingType = sellingType;
        this.name = name;
        this.price = price;
    }
}
