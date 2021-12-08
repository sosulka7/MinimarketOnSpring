package com.koshelev.spring.web.services;

import com.koshelev.spring.web.entities.Product;
import com.koshelev.spring.web.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

//    просто фильтрация по минимальной и максимальной цене (работает)
//    public List<Product> findProductsByCostBetween(Double min, Double max){
//        return productRepository.findAllByCostBetween(min, max);
//    }

    public Page<Product> findProductsByMinAndMaxCost(Double minCost, Double maxCost, Integer pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Product> page = productRepository.findAllByCostBetween(minCost, maxCost, pageable);
        return page;
    }


    public void createNewProduct(Product product){
        productRepository.save(product);
    }
}
