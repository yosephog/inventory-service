package com.products.inventoryservice.service;

import com.products.inventoryservice.data.Product;
import com.products.inventoryservice.exception.ProductAlreadyExistException;
import com.products.inventoryservice.exception.ProductNotFoundException;
import com.products.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product getProductBySerialNumber(String serialNumber) {
        return productRepository.findProductBySerialNumber(serialNumber)
                .orElseThrow(() -> new ProductNotFoundException(serialNumber));
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.valueOf(id)));
    }

    public Product registerProduct(Product product) {
        if(productRepository.findProductBySerialNumber(product.serialNumber()).isPresent()){
            throw new ProductAlreadyExistException(product.serialNumber());
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.findProductBySerialNumber(product.serialNumber())
                .map(existingProduct -> {
                    var productToUpdate = new Product(
                            existingProduct.id(),
                            existingProduct.serialNumber(),
                            product.price(),
                            existingProduct.createdDate(),
                            existingProduct.lastModifiedDate(),
                            existingProduct.version()

                    );
                    return productRepository.save(productToUpdate);
                })
                .orElseGet(() -> registerProduct(product));
    }

    public void removeProduct(String serialNumber) {
        productRepository.deleteBySerialNumber(serialNumber);
    }
}
