package com.koshelev.spring.web.services;

import com.koshelev.spring.web.dto.ProductDto;
import com.koshelev.spring.web.entities.Product;
import com.koshelev.spring.web.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.repositories.ProductRepository;
import com.koshelev.spring.web.repositories.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> findAll(Integer minCost, Integer maxCost, String titlePart, String category, Integer numberPage) {
        Specification<Product> spec = Specification.where(null);
        if (minCost != null) {
            spec = spec.and(ProductSpecifications.costGreaterThanOrEqualsIndicate(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductSpecifications.costLessThanOrEqualsIndicate(maxCost));
        }
        if (titlePart != null) {
            spec = spec.and(ProductSpecifications.titleLike(titlePart));
        }
        if (category != null) {
            spec = spec.and(ProductSpecifications.categoryEquals(category));
        }
        return productRepository.findAll(spec, PageRequest.of(numberPage - 1, 10));
    }



    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Попытка обновить продукт не удалась. Продукт с таким id не найден в базе. id: " + productDto.getId()));
        product.setCost(productDto.getCost());
        product.setTitle(productDto.getTitle());
        return product;
    }

    //Как бы и сущность переименовывать не хотелось бы и сгенерированный класс тоже
    //поэтому оставил так. Не знаю, как лучше сделать.
    public List<com.koshelev.spring.web.soap.products.Product> getAllProductsSoap(){
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public com.koshelev.spring.web.soap.products.Product getProductByIdSoap(Long id){
        return productRepository.findById(id).map(functionEntityToSoap).get();
    }

    public static final Function<Product, com.koshelev.spring.web.soap.products.Product> functionEntityToSoap = p -> {
        com.koshelev.spring.web.soap.products.Product product = new com.koshelev.spring.web.soap.products.Product();
        product.setId(p.getId());
        product.setTitle(p.getTitle());
        product.setCost(p.getCost());
        return product;
    };


}
