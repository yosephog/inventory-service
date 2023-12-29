package com.products.inventoryservice.serialization;

import com.products.inventoryservice.data.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ProductJsonTest {
    @Autowired
    private JacksonTester<Product> json;

    @Test
    void testSerialize()throws Exception{
        var product = new Product(1L,"123", 350.0, Instant.now(),Instant.now(),1);
        var jsonContent = json.write(product);
        assertThat(jsonContent).extractingJsonPathStringValue("@.serialNumber").isEqualTo(product.serialNumber());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(product.price());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
                {
                    "serialNumber" : "1234",
                    "price" : 100.0
                }
                """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(Product.of("1234",100.0));
    }
}
