package com.products.inventoryservice.data;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;

import java.time.Instant;

public record Inventory(
        @Id
        Long id,

        @NotBlank
        String serialNumber,

        int countInStock,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate,
        @Version
        int version
) {
    public static Inventory of(String serialNumber, int countInStock){
        return new Inventory(null, serialNumber, countInStock, null, null, 0);
    }
}
