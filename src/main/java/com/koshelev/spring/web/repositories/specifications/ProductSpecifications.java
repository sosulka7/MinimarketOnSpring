package com.koshelev.spring.web.repositories.specifications;

import com.koshelev.spring.web.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> costLessThanOrEqualsIndicate(Integer cost){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost));
    }

    public static Specification<Product> costGreaterThanOrEqualsIndicate(Integer cost){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost));
    }
    public static Specification<Product> titleLike (String titlePart){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)));
    }

    public static Specification<Product> categoryEquals(String category){
        return (((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("title"), category)));
    }
}
