package com.products.inventoryservice.controllers;

import com.products.inventoryservice.exception.ProductNotFoundException;
import com.products.inventoryservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void whenGettingProductNotExistingShouldReturn404() throws Exception {
        String serialNumber = "321";
        given(productService.getProductBySerialNumber(serialNumber))
                .willThrow(ProductNotFoundException.class);
        mockMvc
                .perform(get("/product/serialNumber").param("serialNumber", serialNumber))
                .andExpect(status().isNotFound());
    }
}
