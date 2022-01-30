package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.model.Product;
import com.example.inventariobp.model.StoreProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IStoreProductService {

    Optional<StoreProduct> getStoreProduct(Long id);

    List<Product> getAllProductsByStore(Long storeId);

    StoreProduct saveStoreProduct(StoreProduct dto);

    Long deleteStoreProduct(Long id);
}
