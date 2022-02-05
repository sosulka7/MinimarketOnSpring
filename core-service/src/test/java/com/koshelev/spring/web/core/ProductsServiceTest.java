package com.koshelev.spring.web.core;

import com.koshelev.spring.web.core.entities.Product;
import com.koshelev.spring.web.core.repositories.ProductRepository;
import com.koshelev.spring.web.core.repositories.specifications.ProductSpecifications;
import com.koshelev.spring.web.core.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductsServiceTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void findAllTest(){
        //Что-то запутался в том, как протестить спецификацию ProductService.
        //Нельзя заглушить окончательный метод, который вызывает ProductRepository

//        Specification<Product> spec = Specification.where(null);
//        spec
//                .and(ProductSpecifications.costGreaterThanOrEqualsIndicate(100))
//                .and(ProductSpecifications.costLessThanOrEqualsIndicate(300));
//        List<Product> products = new ArrayList<>(Arrays.asList(
//                new Product(1L, "Product1", 100),
//                new Product(2L, "Product2", 150),
//                new Product(3L, "Product3", 300)
//        ));
//        Mockito.doReturn(List.of(products)).when(productRepository.findAll(spec, PageRequest.of(1, 10)));
//        Assertions.assertEquals(3, productService.findAll(99, 301, null, null, 1));
    }
}
