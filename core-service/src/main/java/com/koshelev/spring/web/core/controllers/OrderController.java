package com.koshelev.spring.web.core.controllers;

import com.koshelev.spring.web.api.cart.CartDto;
import com.koshelev.spring.web.core.converters.OrderConverter;

import com.koshelev.spring.web.api.core.OrderDetailsDto;
import com.koshelev.spring.web.api.core.OrderDto;
import com.koshelev.spring.web.core.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDetailsDto orderDetailsDto, @RequestHeader String username){
        orderService.createOrder(orderDetailsDto, username);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader String username){
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }

}
