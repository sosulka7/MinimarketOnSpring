package com.koshelev.spring.web.core.controllers;

import com.koshelev.spring.web.api.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.core.converters.ProductConverter;
import com.koshelev.spring.web.api.core.ProductDto;
import com.koshelev.spring.web.core.entities.Product;
import com.koshelev.spring.web.core.services.ProductService;
import com.koshelev.spring.web.core.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @GetMapping
    public Page<ProductDto> getProducts(@RequestParam (name = "min_price",required = false) Integer minPrice,
                                        @RequestParam (name = "max_price",required = false) Integer maxPrice,
                                        @RequestParam (name = "title_part",required = false) String titlePart,
                                        @RequestParam (name = "category", required = false) String category,
                                        @RequestParam (defaultValue = "1") Integer pageNumber){
        if (pageNumber < 1){
            pageNumber = 1;
        }
        return productService.findAll(minPrice, maxPrice, titlePart, category, pageNumber).map(productConverter::entityToDto);
    }


    @PostMapping("/ids")
    public List<ProductDto> getProducts(@RequestBody List<Long> productsId){
        return productService.getProductByListId(productsId).stream().map(productConverter::entityToDto).collect(Collectors.toList());
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
