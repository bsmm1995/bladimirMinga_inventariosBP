package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.model.TransactionDetail;

import java.util.List;
import java.util.Optional;

public interface ITransactionDetailService {

    Optional<TransactionDetail> getTransactionDetail(Long id);

    List<TransactionDetail> getAllTransactionsDetail();

    TransactionDetail saveTransactionDetail(TransactionDetail dto);

    Long deleteTransactionDetail(Long id);
}
