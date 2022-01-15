package com.example.inventariobp.service;

import com.example.inventariobp.controller.ProductController;
import com.example.inventariobp.model.ProductDTO;
import com.example.inventariobp.model.vo.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {

    @MockBean
    private ProductService productServiceMock;

    @Autowired
    private ProductController productController;

    @Test
    void getProduct() {
        ProductDTO product = new ProductDTO(1L, "prod-1", "prod-name-1", 5.5, 10.0);

        when(productServiceMock.getProduct(1L)).thenReturn(Optional.of(product));

        ResponseEntity<Response> result = productController.getProduct(1L);
        Optional<ProductDTO> productDTO = (Optional<ProductDTO>) result.getBody().getAuto();

        assertEquals(product, productDTO.get());

        ResponseEntity<Response> result2 = productController.getProduct(888L);
        Optional<ProductDTO> productDTO2 = (Optional<ProductDTO>) result2.getBody().getAuto();

        assertEquals(false, productDTO2.isPresent());
    }

    @Test()
    void updateStockProduct() {
        ProductDTO product = new ProductDTO(1L, "prod-1", "prod-name-1", 5.5, 11.0);

        when(productServiceMock.updateStockProduct(product.getId(), product.getStock())).thenReturn(product);

        ResponseEntity<Response> result = productController.updateStockProduct(product);
        ProductDTO productDTO = (ProductDTO) result.getBody().getAuto();

        assertEquals(product, productDTO);
    }
}