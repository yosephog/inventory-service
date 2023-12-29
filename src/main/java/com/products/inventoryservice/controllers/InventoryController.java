package com.products.inventoryservice.controllers;

import com.products.inventoryservice.data.Inventory;
import com.products.inventoryservice.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    public Inventory getInventoryBySerialNumber(@RequestParam("serialNumber") String serialNumber){
        return inventoryService.getProductFromInventory(serialNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory registerInventory(@Valid @RequestBody Inventory inventory) {
        return inventoryService.registerProductToTheInventory(inventory);
    }

    @PutMapping
    public Inventory updateInventory(@RequestParam("serialNumber") String serialNumber,
                                     @RequestParam("countInStock") int countInStock) {
        return inventoryService.updateProductCount(serialNumber, countInStock);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInventory(@RequestParam("serialNumber") String serialNumber) {
        inventoryService.deleteInventory(serialNumber);
    }
}
