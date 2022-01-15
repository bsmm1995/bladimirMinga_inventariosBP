package com.example.inventariobp.service;

import com.example.inventariobp.model.CustomerDTO;
import com.example.inventariobp.model.TransactionDTO;
import com.example.inventariobp.model.vo.ReportDetailVO;
import com.example.inventariobp.repository.ITransactionRepository;
import com.example.inventariobp.service.interfaces.ICustomerService;
import com.example.inventariobp.service.interfaces.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;
    private final ICustomerService customerService;
    private final EntityManager entityManager;

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

    @Override
    public Map<String, Object> getDataForCSVReport(String clienteDNI, Date startDate, Date endDate) {
        Map<String, Object> response = new HashMap<>();
        CustomerDTO customer = customerService.getCustomerByDNI(clienteDNI);
        if (customer == null)
            throw new IllegalStateException(String.format("Customer with DNI %s does not exist", clienteDNI));

        if (startDate.equals(endDate) || startDate.after(endDate))
            throw new IllegalStateException("The start date cannot be greater than or equal to the end date");

        response.put("customer", customer);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT TRA.ID, TRA.DATE, CONCAT(STO.COD, ' - ', STO.NAME) AS STORE, PRO.COD, PRO.NAME, TRD.PRICE, TRD.QUANTITY, (TRD.PRICE * TRD.QUANTITY) AS TOTAL ")
                .append("FROM TRANSACTION TRA ")
                .append("INNER JOIN TRANSACTION_DETAIL TRD ")
                .append("ON TRD.TRANSACTION_ID = TRA.ID ")
                .append("INNER JOIN STORE STO ")
                .append("ON STO.ID = TRA.STORE_ID ")
                .append("INNER JOIN PRODUCT PRO ")
                .append("ON PRO.ID = TRD.PRODUCT_ID ")
                .append("WHERE ")
                .append("TRA.CUSTOMER_ID = :clienteId ")
                .append("AND TRA.DATE BETWEEN :startDate AND :endDate ");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("clienteId", customer.getId());
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        List<Object[]> results = query.getResultList();

        List<ReportDetailVO> list = results.stream()
                .map(result -> new ReportDetailVO(((BigInteger) result[0]).longValue(), (Date) result[1],
                        (String) result[2], (String) result[3], (String) result[4], (Double) result[5], (Double) result[6], (Double) result[7]))
                .collect(Collectors.toList());

        response.put("detail", list);

        return response;
    }

    @Override
    public List<Map<String, Object>> getNumberOfTransactionsGroupStoreAndDate() {
        List<Map<String, Object>> response = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) AS NUMBER_TRANSACTIONS, TRA.DATE, TRA.STORE_ID, STO.NAME ")
                .append("FROM TRANSACTION TRA ")
                .append("INNER JOIN STORE STO ")
                .append("ON STO.ID = TRA.STORE_ID ")
                .append("GROUP BY ")
                .append("TRA.STORE_ID, TRA.DATE ");

        Query query = entityManager.createNativeQuery(sql.toString());

        List<Object[]> results = query.getResultList();
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
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT TRA.STORE_ID, STO.NAME AS STORE_NAME, TRD.PRODUCT_ID, PRO.NAME AS PRODUCT_NAME,  (TRD.QUANTITY * TRD.PRICE) AS TOTAL ")
                .append("FROM TRANSACTION TRA ")
                .append("INNER JOIN TRANSACTION_DETAIL TRD ")
                .append("ON TRD.TRANSACTION_ID = TRA.ID ")
                .append("INNER JOIN STORE STO ")
                .append("ON STO.ID = TRA.STORE_ID ")
                .append("INNER JOIN PRODUCT PRO ")
                .append("ON PRO.ID = TRD.PRODUCT_ID ")
                .append("GROUP BY ")
                .append("TRA.STORE_ID, TRD.PRODUCT_ID, TRD.ID ");

        Query query = entityManager.createNativeQuery(sql.toString());

        List<Object[]> results = query.getResultList();
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

    List<Map<String, Object>> updateList(List<Map<String, Object>> listResult, Long productoId, Double increase) {

        return listResult;
    }
}
