package com.koshelev.spring.web.rec.repositories;

import com.koshelev.spring.web.rec.entities.PopularItemInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopularItemInOrderRepository extends JpaRepository<PopularItemInOrder, Long> {


    //удалить все записи старше одного месяца
    @Modifying
    @Query(
            value = "delete from popular_items_in_order where added_at < now() - interval 1 month",
            nativeQuery = true)
    void deleteOldEntries();


    @Query(
            value = "select product_id from popular_items_in_order group by product_id order by count (*) desc limit 5",
            nativeQuery = true)
    List<Long> findFirstFiveWithManyEntries();
}
