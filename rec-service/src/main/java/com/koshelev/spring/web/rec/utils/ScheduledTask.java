package com.koshelev.spring.web.rec.utils;


import com.koshelev.spring.web.rec.services.PopularItemInCartService;
import com.koshelev.spring.web.rec.services.PopularItemInOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@EnableScheduling
@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final PopularItemInOrderService itemInOrderService;
    private final PopularItemInCartService itemInCartService;

    //удаление старых записей раз полчаса
    @Scheduled(fixedRate = 30 * 60 * 1000)
    public void removeOldEntriesForCart(){
        itemInCartService.deleteOldEntries();
    }

    //удаление старых записей раз в полдня
    @Scheduled(fixedRate = 12 * 60 * 60 * 1000)
    public void removeOldEntriesForOrder(){
        itemInOrderService.deleteOldEntries();
    }
}
