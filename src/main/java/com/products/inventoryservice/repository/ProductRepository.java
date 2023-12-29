package com.products.inventoryservice.repository;

import com.products.inventoryservice.data.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findProductBySerialNumber(String serialNumber);

    void deleteBySerialNumber(String serialNumber);
}
