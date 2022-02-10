package com.koshelev.spring.web.core.services;

import com.koshelev.spring.web.api.cart.CartDto;
import com.koshelev.spring.web.api.core.ProductDto;
import com.koshelev.spring.web.api.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.api.core.OrderDetailsDto;
import com.koshelev.spring.web.core.entities.Order;
import com.koshelev.spring.web.core.entities.OrderItem;
import com.koshelev.spring.web.core.integrations.CartServiceIntegration;
import com.koshelev.spring.web.core.integrations.RecServiceIntegration;
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
    private final CartServiceIntegration cartServiceIntegration;
    private final RecServiceIntegration recServiceIntegration;

    @Transactional
    public void createOrder(OrderDetailsDto odd, String username){
        CartDto cartDto = cartServiceIntegration.getUserCart(username);
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
        recServiceIntegration.addProductToRecService(items.stream().map(i -> i.getProduct().getId()).collect(Collectors.toList()));
        cartServiceIntegration.clearUserCart(username);
    }

    public List<Order> findOrdersByUsername(String username){
        return orderRepository.findAllByUsername(username);
    }
}
