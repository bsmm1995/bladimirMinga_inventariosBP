package com.example.inventariobp.repository;

import com.example.inventariobp.model.TransactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<TransactionDTO, Long> {
}
