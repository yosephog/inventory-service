package com.products.inventoryservice.exception;

public class InventoryAlreadyExistException extends RuntimeException {

    public InventoryAlreadyExistException(String value) {
        super("The inventory with the value " + value + " already exists.");
    }
}
