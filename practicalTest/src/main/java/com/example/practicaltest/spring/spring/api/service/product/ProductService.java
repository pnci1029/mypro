package com.example.practicaltest.spring.spring.api.service.product;

import com.example.practicaltest.spring.spring.api.controller.product.dto.request.ProductCreateRequest;
import com.example.practicaltest.spring.spring.api.service.product.request.ProductCreateServiceRequest;
import com.example.practicaltest.spring.spring.api.service.product.response.ProductResponse;
import com.example.practicaltest.spring.spring.domain.product.Product;
import com.example.practicaltest.spring.spring.domain.product.ProductRepository;
import com.example.practicaltest.spring.spring.domain.product.ProductSellingType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Transactional(readOnly = true) -> 읽기전용 (CUD 작업 생략)
 *
 * JPA -> 1차 캐에 변경감 / 스냅샷 저장을 하지 않음(성능 향상)
 * CQRS -> command / query 서비스들 명시적으로 분리 처리
 *
 * 아래와 같이 사용 가능
 */

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ProductService  {

    private final ProductRepository productRepository;
    private final ProductNumberFactory productNumberFactory;

    @Transactional
    public ProductResponse createProduct(ProductCreateServiceRequest request) {
        // 상품 번호 생성 / 001, 002 ,003 ...
        // db를 읽어 가장 마지막 상품번호 +1

        String newProductNo = productNumberFactory.createNewProductNo();
        Product product = request.toEntity(newProductNo);

        Product resultProduct = productRepository.save(product);
        return ProductResponse.of(resultProduct);
    }

    public List<ProductResponse> getSellingProducts() {

        List<Product> productList = productRepository.findAllBySellingTypeIn(ProductSellingType.forDisplay());
        return productList.stream()
                .map(item -> ProductResponse.of(item))
                .collect(Collectors.toList());
    }

}
