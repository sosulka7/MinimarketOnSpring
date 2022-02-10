package com.koshelev.spring.web.cart.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RecServiceIntegration {

    private final WebClient recServiceWebClient;

    public void addProductToRecService(Long productsId){
        recServiceWebClient.post()
                .uri("/api/v1/in_cart/" + productsId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
