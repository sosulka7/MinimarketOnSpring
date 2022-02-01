package com.koshelev.spring.web.core.validators;

import com.koshelev.spring.web.api.dto.ProductDto;
import com.koshelev.spring.web.core.exceptions.ValidationException;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto){
        List<String> errors = new ArrayList<>();
        if(productDto.getCost() < 1){
            errors.add("Цена продукта не может быть меньше 1");
        }
        if (productDto.getTitle().isBlank()){
            errors.add("Продукт не может иметь пустое название");
        }
        if (!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}
