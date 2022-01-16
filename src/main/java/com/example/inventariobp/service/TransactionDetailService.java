package com.example.inventariobp.service;

import com.example.inventariobp.model.TransactionDetail;
import com.example.inventariobp.repository.interfaces.ITransactionDetailRepository;
import com.example.inventariobp.service.interfaces.ITransactionDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionDetailService implements ITransactionDetailService {

    private final ITransactionDetailRepository transactionDetailRepository;

    @Override
    public Optional<TransactionDetail> getTransactionDetail(Long id) {
        return transactionDetailRepository.findById(id);
    }

    @Override
    public List<TransactionDetail> getAllTransactionsDetail() {
        return transactionDetailRepository.findAll();
    }

    @Override
    public TransactionDetail saveTransactionDetail(TransactionDetail dto) {
        return transactionDetailRepository.save(dto);
    }

    @Override
    public Long deleteTransactionDetail(Long id) {
        transactionDetailRepository.deleteById(id);
        return id;
    }
}
