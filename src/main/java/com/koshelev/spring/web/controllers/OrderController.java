package com.koshelev.spring.web.controllers;

import com.koshelev.spring.web.converters.OrderConverter;
import com.koshelev.spring.web.converters.OrderItemConverter;
import com.koshelev.spring.web.dto.OrderDetailsDto;
import com.koshelev.spring.web.dto.OrderDto;
import com.koshelev.spring.web.entities.User;
import com.koshelev.spring.web.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.services.OrderService;
import com.koshelev.spring.web.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDetailsDto orderDetailsDto, Principal principal){
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Пользователь с никнеймом " + principal.getName() + "не найден"));
        orderService.createOrder(orderDetailsDto, user);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(Principal principal){
        return orderService.findOrdersByUsername(principal.getName()).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
