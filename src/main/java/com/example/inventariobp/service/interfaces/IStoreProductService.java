package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.model.ProductDTO;
import com.example.inventariobp.model.StoreProductDTO;

import java.util.List;
import java.util.Optional;

public interface IStoreProductService {

    Optional<StoreProductDTO> getStoreProduct(Long id);

    List<ProductDTO> getAllProductsByStore(Long storeId);

    StoreProductDTO saveStoreProduct(StoreProductDTO dto);

    Long deleteStoreProduct(Long id);
}
