package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.model.TransactionDetailDTO;

import java.util.List;
import java.util.Optional;

public interface ITransactionDetailService {

    Optional<TransactionDetailDTO> getTransactionDetail(Long id);

    List<TransactionDetailDTO> getAllTransactionsDetail();

    TransactionDetailDTO saveTransactionDetail(TransactionDetailDTO dto);

    Long deleteTransactionDetail(Long id);
}
