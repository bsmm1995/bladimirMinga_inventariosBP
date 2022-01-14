package com.example.inventariobp.service;

import com.example.inventariobp.model.TransactionDTO;
import com.example.inventariobp.repository.ITransactionRepository;
import com.example.inventariobp.service.interfaces.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;

    @Override
    public Optional<TransactionDTO> getTransaction(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public TransactionDTO saveTransaction(TransactionDTO dto) {
        return transactionRepository.save(dto);
    }

    @Override
    public Long deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
        return id;
    }
}
