package com.koshelev.spring.web.services;

import com.koshelev.spring.web.dto.OrderDetailsDto;
import com.koshelev.spring.web.entities.Order;
import com.koshelev.spring.web.entities.OrderItem;
import com.koshelev.spring.web.entities.User;
import com.koshelev.spring.web.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CartService cartService;
    private final OrderItemService orderItemService;

    @Transactional
    public void createOrder(OrderDetailsDto odd, String username){
        User user = userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException(String.format("Пользователь с никнеймом %s не найден.", username)));
        Order order = new Order(user, cartService.getCurrentCart().getTotalPrice(), odd.getAddress(), odd.getPhoneNumber());
        orderRepository.save(order);
        orderItemService.saveOrderItems(cartService.getCurrentCart().getItems(), order);
    }
}
