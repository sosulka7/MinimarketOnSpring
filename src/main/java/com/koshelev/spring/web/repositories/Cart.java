package com.koshelev.spring.web.repositories;

import com.koshelev.spring.web.entities.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Cart {

    private List<Product> productList;

    @PostConstruct
    public void init(){
        productList = new ArrayList<>();
    }

    public void add(Product product){
        productList.add(product);
    }

    public void delete(Product product){
        productList.remove(product);
    }

    public List<Product> getProductList(){

        return Collections.unmodifiableList(productList);
    }
}
