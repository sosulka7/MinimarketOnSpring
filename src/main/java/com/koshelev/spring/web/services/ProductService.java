package com.koshelev.spring.web.services;

import com.koshelev.spring.web.data.Product;
import com.koshelev.spring.web.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductList(){
        return productRepository.getProductList();
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }
    public void changeCost(Long productId, Double delta){
        Product product = productRepository.getProductById(productId);
        if (product.getCost() + delta > 0){
            product.setCost(product.getCost() + delta);
        }else {
          //отправить предупреждение, что цена не может быть ниже нуля
        }
    }

}
