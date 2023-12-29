package com.products.inventoryservice.service;

import com.products.inventoryservice.data.Inventory;
import com.products.inventoryservice.exception.InventoryAlreadyExistException;
import com.products.inventoryservice.exception.InventoryNotFoundException;
import com.products.inventoryservice.exception.ProductNotFoundException;
import com.products.inventoryservice.repository.InventoryRepository;
import com.products.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private  final ProductRepository productRepository;
    public Inventory getProductFromInventory(String serialNumber) {
        return inventoryRepository.findInventoryBySerialNumber(serialNumber)
                .orElseThrow(() -> new InventoryNotFoundException(serialNumber));
    }
    public Inventory registerProductToTheInventory(Inventory inventory) {
        if(productRepository.findProductBySerialNumber(inventory.serialNumber()).isEmpty()){
            throw new ProductNotFoundException(inventory.serialNumber());
        }
        return inventoryRepository.save(inventory);
    }
    public Inventory updateProductCount(String serialNumber, int count) {
        if(inventoryRepository.findInventoryBySerialNumber(serialNumber).isPresent()){
            return inventoryRepository.updateInventoryCountInStock(serialNumber, count);
        }
        throw new InventoryNotFoundException(serialNumber);
    }

    public void deleteInventory(String serialNumber) {
        inventoryRepository.deleteBySerialNumber(serialNumber
        );
    }
}
