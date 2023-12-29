package com.products.inventoryservice.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

public record Product(
        @Id
        Long id,

        @NotBlank(message = "Serial number must be defined")
        String serialNumber,

        @NotNull(message = "Product price must be defined")
        @Positive(message = "Product price must be greater thatn zero")
        Double price,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate,

        @Version
        int version

) {
    public static Product of(String serialNumber, Double price){
        return new Product(null, serialNumber, price, null, null,0);
    }
}
