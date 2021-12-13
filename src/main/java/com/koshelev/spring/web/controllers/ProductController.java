package com.koshelev.spring.web.controllers;

import com.koshelev.spring.web.dto.ProductDto;
import com.koshelev.spring.web.entities.Product;
import com.koshelev.spring.web.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> getProducts(@RequestParam (required = false) Double minCost,
                                        @RequestParam (required = false) Double maxCost,
                                        @RequestParam (required = false) String titlePart,
                                        @RequestParam (defaultValue = "1") Integer pageNumber){
        if (pageNumber < 1){
            pageNumber = 1;
        }
        return productService.find(minCost, maxCost, titlePart, pageNumber).map(p -> new ProductDto(p));
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return new ProductDto(productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product with ID - " + id + " not found")));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto){
        Product product = new Product(productDto);
        productService.save(product);
        return new ProductDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto product){
        productService.save(new Product(product));
        return product;
    }
}
