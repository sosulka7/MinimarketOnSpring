package com.koshelev.spring.web.controllers;

import com.koshelev.spring.web.dto.Cart;
import com.koshelev.spring.web.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCurrentCart (){
        return cartService.getCurrentCart();
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id){
        cartService.addProductByIdToCart(id);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cartService.clear();
    }

    @GetMapping("/change_quantity")
    public void changeQuantity(@RequestParam Long productId, @RequestParam Integer delta){
        cartService.changeQuantity(productId, delta);
    }

}
