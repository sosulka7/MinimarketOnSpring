package com.koshelev.spring.web.core;

import com.koshelev.spring.web.api.cart.CartDto;
import com.koshelev.spring.web.api.cart.CartItemDto;
import com.koshelev.spring.web.api.core.OrderItemDto;
import com.koshelev.spring.web.api.core.OrderDetailsDto;
import com.koshelev.spring.web.core.entities.Product;
import com.koshelev.spring.web.core.services.OrderService;
import com.koshelev.spring.web.core.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
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
        List<CartItemDto> orderItems = new ArrayList<>(Arrays.asList(
                new CartItemDto(1L, "Product#1", 4, new BigDecimal(25), new BigDecimal(25))
        ));
        cartDto.setItems(orderItems);
        cartDto.setTotalPrice(new BigDecimal(100));
        OrderDetailsDto odd = new OrderDetailsDto();
        odd.setAddress("qwerty");
        odd.setPhoneNumber("88005553535");
        Product product = new Product(1L, "Product#1", new BigDecimal(25.00));
        Mockito.when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        orderService.createOrder(odd, "username");
    }
}
