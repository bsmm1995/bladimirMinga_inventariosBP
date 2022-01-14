package com.example.inventariobp.repository;

import com.example.inventariobp.model.TransactionDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionDetailRepository extends JpaRepository<TransactionDetailDTO, Long> {
}
