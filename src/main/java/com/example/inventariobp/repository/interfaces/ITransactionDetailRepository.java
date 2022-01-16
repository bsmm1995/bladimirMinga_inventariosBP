package com.example.inventariobp.repository.interfaces;

import com.example.inventariobp.model.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {
}
