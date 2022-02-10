package com.koshelev.spring.web.rec.controllers;

import com.koshelev.spring.web.api.core.ProductDto;
import com.koshelev.spring.web.rec.services.PopularItemInCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/in_cart")
@RequiredArgsConstructor
public class PopularItemInCartController {

    private final PopularItemInCartService itemInCartService;

    @GetMapping
    public List<ProductDto> getFirstFivePopularItem(){
        return itemInCartService.getFirstFivePopularItem();
    }

    @PostMapping("/{productId}")
    public void addItem(@PathVariable Long productId){
        itemInCartService.addItem(productId);
    }


}
