package com.example.inventariobp.repository;

import com.example.inventariobp.model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductDTO, Long> {
}
