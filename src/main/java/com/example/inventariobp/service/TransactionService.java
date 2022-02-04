package com.example.inventariobp.service;

import com.example.inventariobp.data.Customer;
import com.example.inventariobp.data.Transaction;
import com.example.inventariobp.data.dto.ReportDetailDTO;
import com.example.inventariobp.repository.interfaces.ICustomerRepository;
import com.example.inventariobp.repository.interfaces.IReportsRepository;
import com.example.inventariobp.repository.interfaces.ITransactionRepository;
import com.example.inventariobp.service.interfaces.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;
    private final IReportsRepository transactionComplementaryRepository;
    private final ICustomerRepository customerRepository;

    @Override
    public Optional<Transaction> getTransaction(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction saveTransaction(Transaction dto) {
        return transactionRepository.save(dto);
    }

    @Override
    public Long deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
        return id;
    }

    @Override
    public Map<String, Object> getDataForCSVReport(String clienteDNI, Date startDate, Date endDate) {

        Optional<Customer> customer = customerRepository.findByDni(clienteDNI);

        if (!customer.isPresent())
            throw new IllegalStateException(String.format("Customer with DNI %s does not exist", clienteDNI));

        if (startDate.equals(endDate) || startDate.after(endDate))
            throw new IllegalStateException("The start date cannot be greater than or equal to the end date");

        Map<String, Object> resultMap = new HashMap<>();

        List<Object[]> results = transactionComplementaryRepository.getDataForCSVReport(customer.get().getId(), startDate, endDate);
        List<ReportDetailDTO> list = results.stream()
                .map(result -> new ReportDetailDTO(((BigInteger) result[0]).longValue(), (Date) result[1],
                        (String) result[2], (String) result[3], (String) result[4], (Double) result[5], (Double) result[6], (Double) result[7]))
                .collect(Collectors.toList());

        resultMap.put("customer", customer.get());
        resultMap.put("detail", list);

        return resultMap;
    }

    @Override
    public List<Map<String, Object>> getNumberOfTransactionsGroupStoreAndDate() {
        List<Map<String, Object>> response = new ArrayList<>();
        List<Object[]> results = transactionComplementaryRepository.getNumberOfTransactionsGroupStoreAndDate();
        results.forEach(e -> {
            Map<String, Object> tmpMap = new HashMap<>();
            tmpMap.put("nTransactions", e[0]);
            tmpMap.put("date", e[1]);
            tmpMap.put("storeId", e[2]);
            tmpMap.put("storeName", e[3]);
            response.add(tmpMap);
        });
        return response;
    }

    @Override
    public List<Map<String, Object>> getSoldByStoreAndProduct() {
        List<Map<String, Object>> response = new ArrayList<>();
        List<Object[]> results = transactionComplementaryRepository.getSoldByStoreAndProduct();
        Set storeIdSet = new HashSet<>();

        results.forEach(e -> {
            storeIdSet.add(e[0]);
        });

        storeIdSet.forEach(storeId -> {
            Set productIdSet = new HashSet<>();
            results.forEach(element -> {
                if (storeId.equals(element[0])) {
                    if (productIdSet.contains(element[2])) {
                        response.forEach(e -> {
                            if (e.get("productId").equals(element[2])) {
                                e.put("total", (Double) e.get("total") + (Double) element[4]);
                            }
                        });
                    } else {
                        Map<String, Object> tmpMap = new HashMap<>();
                        tmpMap.put("storeId", element[0]);
                        tmpMap.put("storeName", element[1]);
                        tmpMap.put("productId", element[2]);
                        tmpMap.put("productName", element[3]);
                        tmpMap.put("total", element[4]);
                        response.add(tmpMap);
                        productIdSet.add(element[2]);
                    }
                }
            });
        });

        return response;
    }
}
