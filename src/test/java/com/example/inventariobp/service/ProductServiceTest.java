package com.example.inventariobp.service;

import com.example.inventariobp.model.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {

    @MockBean
    private ProductService productServiceMock;

    @Autowired
    private ProductService productService;

    @Test
    void getProduct() {
        ProductDTO product = new ProductDTO(1L, "prod-1", "prod-name-1", 5.5, 10.0);

        when(productServiceMock.getProduct(1L)).thenReturn(Optional.of(product));

        assertEquals(product, productService.getProduct(1L).get());

        assertEquals(false, productService.getProduct(11L).isPresent());
    }

    @Test()
    void updateStockProduct() {
        ProductDTO product = new ProductDTO(1L, "prod-1", "prod-name-1", 5.5, 11.0);
        when(productServiceMock.updateStockProduct(product.getId(), product.getStock())).thenReturn(product);
        assertEquals(product, productService.updateStockProduct(product.getId(), product.getStock()));
    }
}