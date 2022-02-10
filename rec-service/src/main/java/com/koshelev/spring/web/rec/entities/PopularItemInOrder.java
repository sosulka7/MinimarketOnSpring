package com.koshelev.spring.web.rec.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "popular_items_in_order")
@Data
@NoArgsConstructor
public class PopularItemInOrder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "product_id")
    private Long productId;

    @CreationTimestamp
    @Column(name = "added_at")
    private LocalDateTime addedAt;

    public PopularItemInOrder(Long productId){
        this.productId = productId;
    }
}
