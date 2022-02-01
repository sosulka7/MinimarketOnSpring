package com.koshelev.spring.web.core.services;

import com.koshelev.spring.web.api.dto.CartDto;
import com.koshelev.spring.web.api.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.core.dto.OrderDetailsDto;
import com.koshelev.spring.web.core.entities.Order;
import com.koshelev.spring.web.core.entities.OrderItem;
import com.koshelev.spring.web.core.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Transactional
    public void createOrder(OrderDetailsDto odd, String username, CartDto cartDto){
        Order order = new Order(username, cartDto.getTotalPrice(), odd.getAddress(), odd.getPhoneNumber());
        List<OrderItem> items = cartDto.getItems().stream()
                .map(o -> {
                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setQuantity(o.getQuantity());
                    item.setPrice(o.getPrice());
                    item.setPricePerProduct(o.getPricePerProduct());
                    item.setProduct(productService.getProductById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден. ID: " + o.getProductId())));
                    return item;
                }).collect(Collectors.toList());
        order.setOrderItems(items);
        orderRepository.save(order);
    }

    public List<Order> findOrdersByUsername(String username){
        return orderRepository.findAllByUsername(username);
    }
}
