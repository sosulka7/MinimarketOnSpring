package com.koshelev.spring.web.repositories;

import com.koshelev.spring.web.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

    //    просто фильтрация по минимальной и максимальной цене
//    List<Product> findAllByCostBetween(Double min, Double max);

    Page<Product> findAllByCostBetween(Double min, Double max, Pageable pageable);
}
