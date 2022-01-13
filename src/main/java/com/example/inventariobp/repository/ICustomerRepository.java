package com.example.inventariobp.repository;

import com.example.inventariobp.model.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<CustomerDTO, Long> {
}
