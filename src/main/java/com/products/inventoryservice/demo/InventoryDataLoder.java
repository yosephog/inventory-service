package com.products.inventoryservice.demo;

import com.products.inventoryservice.data.Inventory;
import com.products.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
@RequiredArgsConstructor
public class InventoryDataLoder {
    private final InventoryRepository inventoryRepository;

    @EventListener(ApplicationContextEvent.class)
    public void loadInventoryTestData(){
        inventoryRepository.deleteAll();
        inventoryRepository.saveAll(
                List.of(Inventory.of("123", 20),
                        Inventory.of("321", 50))
        );
    }
}
