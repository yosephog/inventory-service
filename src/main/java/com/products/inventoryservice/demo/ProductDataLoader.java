package com.products.inventoryservice.demo;

import com.products.inventoryservice.data.Product;
import com.products.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
@RequiredArgsConstructor
public class ProductDataLoader {
    private final ProductRepository productRepository;

    @EventListener(ApplicationContextEvent.class)
    public void loadProductTestData(){
        productRepository.deleteAll();
        var products = List.of(
                Product.of("123",340.5),
                Product.of("321",400.0)
        );
        productRepository.saveAll(products);
    }
}
