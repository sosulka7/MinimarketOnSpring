package com.koshelev.spring.web.core.services;

import com.koshelev.spring.web.core.entities.OrderItem;
import com.koshelev.spring.web.core.repositories.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    public void save(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }
}
