package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.model.ProductDTO;
import com.example.inventariobp.model.vo.ProductVO;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Optional<ProductDTO> getProduct(Long id);

    List<ProductVO> getAllProducts();

    ProductDTO saveProduct(ProductDTO dto);

    ProductDTO updateStockProduct(Long id, Double stock);

    Long deleteProduct(Long id);
}
