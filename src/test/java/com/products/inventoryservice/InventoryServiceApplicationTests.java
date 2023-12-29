package com.products.inventoryservice;

import com.products.inventoryservice.data.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenPostRequestThenProductCreated() {
        var expectedProduct = Product.of("123", 350.0);
        webTestClient
                .post()
                .uri("/product")
                .bodyValue(expectedProduct)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Product.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook.serialNumber()).isEqualTo(expectedProduct.serialNumber());
                });
    }

}
