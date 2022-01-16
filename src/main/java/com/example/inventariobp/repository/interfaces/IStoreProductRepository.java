package com.example.inventariobp.repository.interfaces;

import com.example.inventariobp.model.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStoreProductRepository extends JpaRepository<StoreProduct, Long> {
    Optional<StoreProduct> findByStoreIdAndProductId(Long storeId, Long productId);
}
