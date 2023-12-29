package com.products.inventoryservice.exception;

public class ProductAlreadyExistException extends RuntimeException{

    public ProductAlreadyExistException(String value) {
        super("The product with the value " + value + " already exists");
    }
}
