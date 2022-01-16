package com.example.inventariobp.repository.interfaces;

import com.example.inventariobp.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStoreRepository extends JpaRepository<Store, Long> {
}
