package com.koshelev.spring.web.converters;

import com.koshelev.spring.web.dto.ProductDto;
import com.koshelev.spring.web.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product dtoToEntity(ProductDto productDto){
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    }

    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }
}
