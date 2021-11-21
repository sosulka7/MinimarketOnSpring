package com.koshelev.spring.web.controllers;

import com.koshelev.spring.web.data.Product;
import com.koshelev.spring.web.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductRepository(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getProductList();
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @GetMapping("/products/change_cost")
    public void changeCost(@RequestParam Long productId, @RequestParam Double delta){
        productService.changeCost(productId, delta);
    }
}
