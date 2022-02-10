package com.koshelev.spring.web.rec.controllers;

import com.koshelev.spring.web.api.core.ProductDto;
import com.koshelev.spring.web.rec.services.PopularItemInOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/in_order")
@RequiredArgsConstructor
public class PopularItemInOrderController {

    private final PopularItemInOrderService itemInOrderService;

    @GetMapping
    public List<ProductDto> getFirstFivePopularItem(){
        return itemInOrderService.getFirstFivePopularItem();
    }

    @PostMapping()
    public void addItem(@RequestBody List<Long> productId){
        itemInOrderService.addItems(productId);
    }
}
