package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.model.TransactionDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ITransactionService {

    Optional<TransactionDTO> getTransaction(Long id);

    List<TransactionDTO> getAllTransactions();

    TransactionDTO saveTransaction(TransactionDTO dto);

    Long deleteTransaction(Long id);

    Map<String, Object> getDataForCSVReport(String clienteDNI, Date startDate, Date endDate);
}
