package com.koshelev.spring.web.services;

import com.koshelev.spring.web.dto.Cart;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;

    @Transactional
    public void createOrder(OrderDetailsDto odd, User user){
        String cartKey = cartService.getCartUuidFromSuffix(user.getUsername());
        Cart currentCart = cartService.getCurrentCart(cartKey);
        Order order = new Order(user, currentCart.getTotalPrice(), odd.getAddress(), odd.getPhoneNumber());
        List<OrderItem> items = currentCart.getItems().stream()
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
        cartService.clearCart(cartKey);
    }

    public List<Order> findOrdersByUsername(String username){
        return orderRepository.findAllByUsername(username);
    }
}
