package com.koshelev.spring.web.rec.services;


import com.koshelev.spring.web.api.core.ProductDto;
import com.koshelev.spring.web.rec.entities.PopularItemInCart;
import com.koshelev.spring.web.rec.integrations.CoreServiceIntegration;
import com.koshelev.spring.web.rec.repositories.PopularItemInCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopularItemInCartService {

    private final CoreServiceIntegration coreServiceIntegration;
    private final PopularItemInCartRepository itemInCartRepository;

    @Transactional
    public void deleteOldEntries(){
        itemInCartRepository.deleteOldEntries();
    }

    public List<ProductDto> getFirstFivePopularItem(){
        return coreServiceIntegration.getProductDto(findFirstFifeEntries());
    }

    public List<Long> findFirstFifeEntries(){
        return itemInCartRepository.findFirstFiveWithManyEntries();
    }

    public void addItem(Long productId) {
        PopularItemInCart item = new PopularItemInCart(productId);
        itemInCartRepository.save(item);
    }
}
