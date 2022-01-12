package com.koshelev.spring.web.services;

import com.koshelev.spring.web.entities.OrderItem;
import com.koshelev.spring.web.repositories.OrderItemRepository;
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
