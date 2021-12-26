package com.koshelev.spring.web.controllers;

import com.koshelev.spring.web.converters.ProductConverter;
import com.koshelev.spring.web.dto.ProductDto;
import com.koshelev.spring.web.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductConverter productConverter;

    @GetMapping()
    public List<ProductDto> getProducts(){
        return cartService.getAllProducts().stream().map(p -> productConverter.entityToDto(p)).collect(Collectors.toList());
    }

    @GetMapping("/add/{id}")
    public void addProduct(@PathVariable Long id){
        cartService.add(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        cartService.delete(id);
    }
}
