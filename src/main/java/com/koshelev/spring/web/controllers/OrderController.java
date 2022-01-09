package com.koshelev.spring.web.controllers;

import com.koshelev.spring.web.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/create")
    public String createOrder(@RequestParam (name = "phone_number") String phoneNumber,
                            @RequestParam (name = "address") String address){
        System.out.println(phoneNumber + address);
        return "Заказ успешно сформирован";
    }
}
