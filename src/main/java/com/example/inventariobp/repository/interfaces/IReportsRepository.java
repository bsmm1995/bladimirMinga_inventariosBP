package com.example.inventariobp.repository.interfaces;

import com.example.inventariobp.data.Product;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IReportsRepository {
    List<Object[]> getDataForCSVReport(Long clienteId, Date startDate, Date endDate);

    List<Object[]> getSoldByStoreAndProduct();

    List<Object[]> getNumberOfTransactionsGroupStoreAndDate();

    List<Product> getAllProductsByStore(Long storeID);
}
