package com.koshelev.spring.web.controllers;

import com.koshelev.spring.web.entities.Product;
import com.koshelev.spring.web.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getProductList();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product with ID - " + id + " not found"));
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @GetMapping("/products/cost_between")
    public List<Product> getProductsByCostBetween(@RequestParam(defaultValue = "0") Double min, @RequestParam Double max){
        return productService.findProductsByCostBetween(min, max);
    }

    @GetMapping("/products/high_{min}")
    public List<Product> getProductsHighMinCost(@PathVariable Double min){
        return productService.getProductsHighMinCost(min);
    }

    @GetMapping("/products/low_{max}")
    public List<Product> getProductsLowMaxCost(@PathVariable Double max) {
        return productService.getProductsLowMaxCost(max);
    }

    @PostMapping("/products/new_prod")
    public void createNewProduct(@RequestBody Product product){
        productService.createNewProduct(product);
    }
}
