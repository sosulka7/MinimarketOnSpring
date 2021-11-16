package com.koshelev.spring.web.repositories;

import com.koshelev.spring.web.data.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init(){
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Молоко" , 50.00),
                new Product(2L, "Хлеб" , 35.5),
                new Product(3L, "Пряники" , 68.30),
                new Product(4L, "Макароны" , 45.00),
                new Product(5L, "Пельмени" , 200.00)
        ));
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(products);
    }

    public Product getProductById(Long id){
        if (id > 0 && id <= 5){
            return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(()-> new RuntimeException());
        }
        return null;
    }
}
