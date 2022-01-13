package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.model.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Optional<ProductDTO> getProduct(Long id);

    List<ProductDTO> getAllProducts();

    ProductDTO saveProduct(ProductDTO dto);

    Long deleteProduct(Long id);
}
