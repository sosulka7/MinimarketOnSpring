package com.koshelev.spring.web.cart.integrations;

import com.koshelev.spring.web.api.core.ProductDto;
import com.koshelev.spring.web.api.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;



import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient coreServiceWebClient;

    public Optional<ProductDto> findById(Long id){

        //TODO: доделать
        //В этом варианте он отловит ошибку о том, что не смог найти продукт.
        //Но как отловить то, что сервер вообще не дает ответ, я не придумал
            ProductDto productDto = coreServiceWebClient.get()
                    .uri("/api/v1/products/" + id)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, response -> {
                        throw new ResourceNotFoundException("Продукт не найден");
                    })
                    .bodyToMono(ProductDto.class)
                    .block();
            return Optional.ofNullable(productDto);


    }

}
