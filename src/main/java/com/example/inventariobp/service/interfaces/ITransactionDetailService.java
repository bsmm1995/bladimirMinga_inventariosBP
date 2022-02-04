package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.data.TransactionDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ITransactionDetailService {

    Optional<TransactionDetail> getTransactionDetail(Long id);

    List<TransactionDetail> getAllTransactionsDetail();

    TransactionDetail saveTransactionDetail(TransactionDetail dto);

    Long deleteTransactionDetail(Long id);
}
