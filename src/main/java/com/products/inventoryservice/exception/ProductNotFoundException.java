package com.products.inventoryservice.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String value) {
        super("The product with the value " + value + " is not found.");
    }
}
