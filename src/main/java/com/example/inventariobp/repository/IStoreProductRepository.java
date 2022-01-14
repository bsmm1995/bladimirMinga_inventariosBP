package com.example.inventariobp.repository;

import com.example.inventariobp.model.StoreProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStoreProductRepository extends JpaRepository<StoreProductDTO, Long> {
}
