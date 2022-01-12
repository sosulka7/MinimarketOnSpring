package com.koshelev.spring.web.controllers;

import com.koshelev.spring.web.converters.ProductConverter;
import com.koshelev.spring.web.dto.ProductDto;
import com.koshelev.spring.web.entities.Product;
import com.koshelev.spring.web.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.services.ProductService;
import com.koshelev.spring.web.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @GetMapping
    public Page<ProductDto> getProducts(@RequestParam (required = false) Integer minCost,
                                        @RequestParam (required = false) Integer maxCost,
                                        @RequestParam (required = false) String titlePart,
                                        @RequestParam (defaultValue = "1") Integer pageNumber){
        if (pageNumber < 1){
            pageNumber = 1;
        }
        return productService.findAll(minCost, maxCost, titlePart, pageNumber).map(p -> productConverter.entityToDto(p));
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product with ID - " + id + " not found"));
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        productService.save(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }
}
