package com.example.inventariobp.service;

import com.example.inventariobp.model.TransactionDetailDTO;
import com.example.inventariobp.repository.ITransactionDetailRepository;
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
    public Optional<TransactionDetailDTO> getTransactionDetail(Long id) {
        return transactionDetailRepository.findById(id);
    }

    @Override
    public List<TransactionDetailDTO> getAllTransactionsDetail() {
        return transactionDetailRepository.findAll();
    }

    @Override
    public TransactionDetailDTO saveTransactionDetail(TransactionDetailDTO dto) {
        return transactionDetailRepository.save(dto);
    }

    @Override
    public Long deleteTransactionDetail(Long id) {
        transactionDetailRepository.deleteById(id);
        return id;
    }
}
