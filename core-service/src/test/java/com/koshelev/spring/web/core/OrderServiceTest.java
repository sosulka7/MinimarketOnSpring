package com.koshelev.spring.web.core;

import com.koshelev.spring.web.api.dto.CartDto;
import com.koshelev.spring.web.api.dto.OrderItemDto;
import com.koshelev.spring.web.core.dto.OrderDetailsDto;
import com.koshelev.spring.web.core.entities.Order;
import com.koshelev.spring.web.core.entities.Product;
import com.koshelev.spring.web.core.repositories.OrderRepository;
import com.koshelev.spring.web.core.services.OrderService;
import com.koshelev.spring.web.core.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private ProductService productService;


    @Test
    public void createOrderTest(){
        CartDto cartDto = new CartDto();
        List<OrderItemDto> orderItems = new ArrayList<>(Arrays.asList(
                new OrderItemDto(1L, "Product#1", 4, 25, 100)
        ));
        cartDto.setItems(orderItems);
        cartDto.setTotalPrice(100);
        OrderDetailsDto odd = new OrderDetailsDto();
        odd.setAddress("qwerty");
        odd.setPhoneNumber("88005553535");
        Product product = new Product(1L, "Product#1", 25);
        Mockito.when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        orderService.createOrder(odd, "username", cartDto);

        //Как проверить, что заказ создался?
    }
}
