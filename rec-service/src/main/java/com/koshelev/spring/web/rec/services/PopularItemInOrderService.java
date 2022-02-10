package com.koshelev.spring.web.rec.services;

import com.koshelev.spring.web.api.core.ProductDto;
import com.koshelev.spring.web.rec.entities.PopularItemInOrder;
import com.koshelev.spring.web.rec.integrations.CoreServiceIntegration;
import com.koshelev.spring.web.rec.repositories.PopularItemInOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopularItemInOrderService {

    private final PopularItemInOrderRepository itemInOrderRepository;
    private final CoreServiceIntegration coreServiceIntegration;

    @Transactional
    public void deleteOldEntries(){
        itemInOrderRepository.deleteOldEntries();
    }

    public void addItem(Long productId){
        PopularItemInOrder item = new PopularItemInOrder(productId);
        itemInOrderRepository.save(item);
    }

    @Transactional
    public void addItems(List<Long> productsId){
        productsId.stream().forEach(id -> addItem(id));
    }
    public List<ProductDto> getFirstFivePopularItem(){
        return coreServiceIntegration.getProductDto(findFirstFifeEntries());
    }

    public List<Long> findFirstFifeEntries(){
        return itemInOrderRepository.findFirstFiveWithManyEntries();
    }
}
