package com.koshelev.spring.web.controllers;

import com.koshelev.spring.web.entities.Product;
import com.koshelev.spring.web.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;



@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }

//    только фильтрация по минимальной или максимальной цене (работает)
//    @GetMapping("/products")
//    public List<Product> getProducts(@RequestParam (defaultValue = "0", required = false) Double minCost,
//                                     @RequestParam (defaultValue = "10000", required = false) Double maxCost){
//        return productService.findProductsByCostBetween(minCost, maxCost);
//    }

    //попытка пагинации (почти удачная), при переходе на эндпоинт (а не на index.html) и указании номера страницы
    //в RequestParam отображает корректное количество продуктов в виде json'ов
    //проблема где-то в ангуляре :(
    @GetMapping("/products")
    public Page<Product> getProducts(@RequestParam (defaultValue = "0", required = false) Double minCost,
                                     @RequestParam (defaultValue = "10000", required = false) Double maxCost,
                                     @RequestParam (defaultValue = "0", required = false)Integer pageNumber){
        return productService.findProductsByMinAndMaxCost(minCost, maxCost, pageNumber);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product with ID - " + id + " not found"));
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @PostMapping("/products/new_prod")
    public void createNewProduct(@RequestBody Product product){
        productService.createNewProduct(product);
    }
}
