package com.koshelev.spring.web.services;

import com.koshelev.spring.web.entities.Product;
import com.koshelev.spring.web.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductList(){
        return productRepository.findAll();
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public List<Product> findProductsByCostBetween(Double min, Double max){
        return productRepository.findAllByCostBetween(min, max);
    }

    public List<Product> getProductsHighMinCost(Double min){
        return productRepository.findProductsHighMinCost(min);
    }

    public List<Product> getProductsLowMaxCost(Double max){
        return productRepository.findProductsLowMaxCost(max);
    }

    public void createNewProduct(Product product){
        productRepository.save(product);
    }
}
