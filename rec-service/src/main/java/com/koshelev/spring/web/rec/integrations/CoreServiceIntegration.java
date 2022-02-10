package com.koshelev.spring.web.rec.integrations;

import com.koshelev.spring.web.api.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {

    private final WebClient coreServiceWebClient;

    public List<ProductDto> getProductDto(List<Long> productsId){
        List<ProductDto> products = coreServiceWebClient.post()
                .uri("/api/v1/products/ids")
                .bodyValue(productsId)
                .retrieve()
                .bodyToMono(List.class)
                .block();
        return products;
    }
}
