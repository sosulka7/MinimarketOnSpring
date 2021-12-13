package com.koshelev.spring.web.services;

import com.koshelev.spring.web.entities.Product;
import com.koshelev.spring.web.repositories.ProductRepository;
import com.koshelev.spring.web.repositories.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Product> find (Double minCost, Double maxCost, String titlePart, Integer numberPage){
        Specification<Product> spec = Specification.where(null);
        if (minCost != null){
            spec = spec.and(ProductSpecifications.costGreaterThanOrEqualsIndicate(minCost));
        }
        if (maxCost != null){
            spec = spec.and(ProductSpecifications.costLessThanOrEqualsIndicate(maxCost));
        }
        if (titlePart != null){
            spec = spec.and(ProductSpecifications.titleLike(titlePart));
        }
        return productRepository.findAll(spec, PageRequest.of(numberPage - 1, 10));
    }
    public Product save(Product product){
        return productRepository.save(product);
    }

}
