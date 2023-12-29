package com.products.inventoryservice.exception;

public class InventoryNotFoundException extends RuntimeException{

    public InventoryNotFoundException(String value) {
        super("The inventory with the value " + value + " is not found.");
    }
}
