package com.koshelev.spring.web.repositories;

import com.koshelev.spring.web.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCostBetween(Double min, Double max);

    @Query("select p from Product p where p.cost > :minCost")
    List<Product> findProductsHighMinCost(Double minCost);

    @Query("select p from Product p where p.cost < :maxCost")
    List<Product> findProductsLowMaxCost(Double maxCost);
}
