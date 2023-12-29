package com.products.inventoryservice.repository;

import com.products.inventoryservice.data.Inventory;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    Optional<Inventory> findInventoryBySerialNumber(String serialNumber);

    @Modifying
    @Transactional
    @Query("UPDATE inventory SET count_in_stock = :count_in_stock WHERE serial_number = : serial_number")
    Inventory updateInventoryCountInStock(@Param("serial_number") String serialNumber, @Param("count_in_stock") int count);

    @Modifying
    @Transactional
    void deleteBySerialNumber(String serialNumber);
}
