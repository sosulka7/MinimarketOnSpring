package com.koshelev.spring.web.services;

import com.koshelev.spring.web.dto.ProductDto;
import com.koshelev.spring.web.entities.Product;
import com.koshelev.spring.web.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.repositories.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final Cart cart;

    public List<Product> getAllProducts() {
        return cart.getProductList();
    }

    public void add(Long id){
        Product product = productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("При добавлении товара в корзину произошла ошибка." +
                "Product with ID - " + id + " not found"));
        cart.add(product);
    }

    public void delete(Long id){
        Product product = productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("При удалении товара из корзины произошла ошибка." +
                "Product with ID - " + id + " not found"));
        cart.delete(product);
    }
}
