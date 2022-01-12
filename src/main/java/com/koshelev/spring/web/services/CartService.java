package com.koshelev.spring.web.services;

import com.koshelev.spring.web.dto.Cart;
import com.koshelev.spring.web.entities.Product;
import com.koshelev.spring.web.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart cart;

    @PostConstruct
    public void init(){
        cart = new Cart();
    }

    public Cart getCurrentCart(){
        return cart;
    }

    public void addProductByIdToCart(Long productId){
        if (!getCurrentCart().addProduct(productId)){
            Product product = productService.getProductById(productId).orElseThrow(()-> new ResourceNotFoundException("Невозможно добавить продукт в корзину. Продукт не найдет, id: " + productId));
            getCurrentCart().addProduct(product);
        }
    }

    public void clear(){
        getCurrentCart().clear();
    }

    public void changeQuantity(Long productId, int delta){
        if (delta == 1) {
            getCurrentCart().addProduct(productId);
        } else {
            getCurrentCart().decreaseProduct(productId);
        }
    }
}
