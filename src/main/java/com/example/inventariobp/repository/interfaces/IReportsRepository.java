package com.example.inventariobp.repository.interfaces;

import com.example.inventariobp.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface IReportsRepository {
    Map<String, Object> getDataForCSVReport(String clienteDNI, Date startDate, Date endDate);

    List<Object[]> getSoldByStoreAndProduct();

    List<Object[]> getNumberOfTransactionsGroupStoreAndDate();

    List<Product> getAllProductsByStore(Long storeID);
}
