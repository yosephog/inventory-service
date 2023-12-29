package com.products.inventoryservice.controllers;

import com.products.inventoryservice.data.Product;
import com.products.inventoryservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping("/serialNumber")
    public Product getProductBySerialNumber(@RequestParam String serialNumber) {
        return productService.getProductBySerialNumber(serialNumber);
    }

    @GetMapping("/id")
    public Product getProductById(@RequestParam Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product registerProduct(@Valid @RequestBody Product product){
        log.info("Registering a product with serial number " + product.serialNumber());
        return productService.registerProduct(product);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String serialNumber) {
        log.info("Deleting product with serial number " + serialNumber);
        productService.removeProduct(serialNumber);
    }

    @PutMapping
    public Product updateProduct(@Valid @RequestBody Product product){
        log.info("Updateing product with serial number " + product.serialNumber());
        return productService.updateProduct(product);
    }
}
