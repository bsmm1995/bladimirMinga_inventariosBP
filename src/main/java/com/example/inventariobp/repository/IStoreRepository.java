package com.example.inventariobp.repository;

import com.example.inventariobp.model.StoreDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStoreRepository extends JpaRepository<StoreDTO, Long> {
}
