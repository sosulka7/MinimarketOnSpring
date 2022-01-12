package com.koshelev.spring.web.services;

import com.koshelev.spring.web.dto.OrderDetailsDto;
import com.koshelev.spring.web.dto.OrderItemDto;
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
    private final ProductService productService;

    @Transactional
    public void createOrder(OrderDetailsDto odd, String username){
        User user = userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException(String.format("Пользователь с никнеймом %s не найден.", username)));
        Order order = new Order(user, cartService.getCurrentCart().getTotalPrice(), odd.getAddress(), odd.getPhoneNumber());
        orderRepository.save(order);

        for (OrderItemDto oid : cartService.getCurrentCart().getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order);
            orderItem.setProductId(productService.getProductById(oid.getProductId()).orElseThrow(()-> new ResourceNotFoundException("Продукт не найден. ID: " + oid.getProductId())));
            orderItem.setPrice(oid.getPrice());
            orderItem.setQuantity(oid.getQuantity());
            orderItem.setPricePerProduct(oid.getPricePerProduct());
            orderItemService.save(orderItem);
        }
    }
}
