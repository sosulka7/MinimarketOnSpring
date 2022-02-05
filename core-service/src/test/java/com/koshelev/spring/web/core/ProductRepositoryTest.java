package com.koshelev.spring.web.core;

import com.koshelev.spring.web.core.entities.Product;
import com.koshelev.spring.web.core.repositories.ProductRepository;
import com.koshelev.spring.web.core.repositories.specifications.ProductSpecifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findBySpecificationTest(){

        Specification<Product> spec = Specification.where(null);
        spec
                .and(ProductSpecifications.costGreaterThanOrEqualsIndicate(50))
                .and(ProductSpecifications.costLessThanOrEqualsIndicate(150))
                .and(ProductSpecifications.categoryEquals("Milk"));

        List<Product> products = productRepository.findAll(spec);

        //на этой проверке ловлю StackOverflowError и тест не проходит
        for (Product p: products) {
            Assertions.assertEquals("Milk", p.getCategory());
        }
    }
}

