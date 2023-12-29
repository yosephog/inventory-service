package com.products.inventoryservice.repository;

import com.products.inventoryservice.config.DataConfig;
import com.products.inventoryservice.data.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findProductWithSerialNumberWhenExisting() {
        var serialNumber = "1234";
        var product = Product.of(serialNumber,230.0);
        jdbcAggregateTemplate.insert(product);
        var actualProduct = productRepository.findProductBySerialNumber(serialNumber);
        assertThat(actualProduct).isPresent();
        assertThat(actualProduct.get().serialNumber()).isEqualTo(product.serialNumber());
    }
}
