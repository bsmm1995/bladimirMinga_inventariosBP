package com.example.inventariobp.repository;

import com.example.inventariobp.model.Product;
import com.example.inventariobp.repository.interfaces.IReportsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Repository
public class ReportsRepository implements IReportsRepository {

    private final EntityManager entityManager;

    @Override
    public List<Object[]> getDataForCSVReport(Long clienteId, Date startDate, Date endDate) {
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
        query.setParameter("clienteId", clienteId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        return query.getResultList();
    }

    @Override
    public List<Object[]> getSoldByStoreAndProduct() {
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

        return query.getResultList();
    }

    @Override
    public List<Object[]> getNumberOfTransactionsGroupStoreAndDate() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) AS NUMBER_TRANSACTIONS, TRA.DATE, TRA.STORE_ID, STO.NAME ")
                .append("FROM TRANSACTION TRA ")
                .append("INNER JOIN STORE STO ")
                .append("ON STO.ID = TRA.STORE_ID ")
                .append("GROUP BY ")
                .append("TRA.STORE_ID, TRA.DATE ");

        Query query = entityManager.createNativeQuery(sql.toString());

        return query.getResultList();
    }

    @Override
    public List<Product> getAllProductsByStore(Long storeID) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT PRO.* ")
                .append("FROM PRODUCT PRO ")
                .append("INNER JOIN STORE_PRODUCT STP ")
                .append("ON  STP.PRODUCT_ID = PRO.ID ")
                .append("AND STP.STORE_ID = :storeId");

        Query query = entityManager.createNativeQuery(sql.toString(), Product.class);
        query.setParameter("storeId", storeID);

        return query.getResultList();
    }
}
