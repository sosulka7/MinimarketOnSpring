package com.koshelev.spring.web.services;

import com.koshelev.spring.web.dto.OrderItemDto;
import com.koshelev.spring.web.entities.Order;
import com.koshelev.spring.web.entities.OrderItem;
import com.koshelev.spring.web.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.repositories.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    @Transactional
    public void saveOrderItems(List<OrderItemDto> orderItemsDto, Order order){
        for (OrderItemDto oid : orderItemsDto) {
            OrderItem orderItem = new OrderItem(
                    productService.getProductById(oid.getProductId()).orElseThrow(()-> new ResourceNotFoundException("Продукт не найден, ID: " + oid.getProductId())),
                    order,
                    oid.getQuantity(),
                    oid.getPricePerProduct(),
                    oid.getPrice()
            );
            orderItemRepository.save(orderItem);
        }
    }
}
