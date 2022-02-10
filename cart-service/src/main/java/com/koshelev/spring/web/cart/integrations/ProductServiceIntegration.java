package com.koshelev.spring.web.cart.integrations;

import com.koshelev.spring.web.api.cart.CartDto;
import com.koshelev.spring.web.api.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient coreServiceWebClient;

    public Optional<ProductDto> findById(Long id){
        ProductDto productDto = coreServiceWebClient.get()
                .uri("/api/v1/products/" + id)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
        return Optional.ofNullable(productDto);
    }
}
