package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.model.Product;
import com.example.inventariobp.model.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Optional<Product> getProduct(Long id);

    List<ProductDTO> getAllProducts();

    Product saveProduct(Product dto);

    Product updateStockProduct(Long id, Double stock);

    Long deleteProduct(Long id);
}
