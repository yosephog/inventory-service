package com.products.inventoryservice.data;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductValidationTest {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void whenALlFieldsCorrectThenValidationSucceeds() {
        var product = new Product(1L,"123", 350.0, Instant.now(),Instant.now(),1);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenSerialIsDefinedIncorrectlyValidationFail() {
        var product = new Product(1L,"123678", 10.0, Instant.now(),Instant.now(),1);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations).isNotEmpty();
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The serial number format must be valid.");
    }
}
