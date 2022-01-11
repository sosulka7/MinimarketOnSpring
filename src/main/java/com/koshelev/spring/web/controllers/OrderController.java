package com.koshelev.spring.web.controllers;

import com.koshelev.spring.web.dto.OrderDetailsDto;
import com.koshelev.spring.web.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderDetailsDto orderDetailsDto, Principal principal){
        orderService.createOrder(orderDetailsDto, principal.getName());
    }
}
