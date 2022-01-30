package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface ITransactionService {

    Optional<Transaction> getTransaction(Long id);

    List<Transaction> getAllTransactions();

    Transaction saveTransaction(Transaction dto);

    Long deleteTransaction(Long id);

    Map<String, Object> getDataForCSVReport(String clienteDNI, Date startDate, Date endDate);

    List<Map<String, Object>> getNumberOfTransactionsGroupStoreAndDate();

    List<Map<String, Object>> getSoldByStoreAndProduct();
}
