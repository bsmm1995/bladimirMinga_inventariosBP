package com.example.inventariobp.service.complementary;

import com.example.inventariobp.data.*;
import com.example.inventariobp.data.dto.ProductsMockDTO;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ManageMock {

    private final RestTemplate restTemplate;
    private final String URL_LIST_MOCK_TRANSACTIONS = "https://mocki.io/v1/0319d867-4f64-4106-9aaa-17d3bc932869";
    private final String URL_LIST_MOCK_TRANSACTIONS_DETAIL = "https://mocki.io/v1/9b54cba1-9930-4bfc-9b82-ca5c659e7df1";
    private final String URL_LIST_MOCK_STORE_PRODUCTS = "https://mocki.io/v1/70c40514-0124-4d24-be1b-8df8f2d7cfdc";

    private final String URL_LIST_MOCK_CUSTOMERS = "https://mocki.io/v1/993f9b88-6eab-4c5d-ab41-9989788340be";
    private final String URL_LIST_MOCK_PRODUCTS = "https://mocki.io/v1/1e10940d-1c04-42a4-9aff-d8dbe9a683ae";

    private final String URL_MOCK_EXTRA_PRODUCT_10 = "https://mocki.io/v1/62c92dbb-b16e-49ec-9a01-13be552b5c22";
    private final String URL_MOCK_EXTRA_PRODUCT_5 = "https://mocki.io/v1/e6a68ea6-d70e-490d-ad40-bedb338e8076";

    public ManageMock() {
        this.restTemplate = new RestTemplate();
    }

    public List<StoreProduct> loadInitialDataStoreProductsFromMocks() {
        StoreProduct[] result = restTemplate.getForObject(
                URL_LIST_MOCK_STORE_PRODUCTS,
                StoreProduct[].class);
        return Arrays.asList(result);
    }

    public List<TransactionDetail> loadInitialDataTransactionsDetailFromMocks() {
        TransactionDetail[] result = restTemplate.getForObject(
                URL_LIST_MOCK_TRANSACTIONS_DETAIL,
                TransactionDetail[].class);
        return Arrays.asList(result);
    }

    public List<Transaction> loadInitialDataTransactionsFromMocks() {
        Transaction[] result = restTemplate.getForObject(
                URL_LIST_MOCK_TRANSACTIONS,
                Transaction[].class);
        return Arrays.asList(result);
    }

    public List<Product> loadInitialDataProductsFromMocks() {
        ProductsMockDTO result = restTemplate.getForObject(
                URL_LIST_MOCK_PRODUCTS,
                ProductsMockDTO.class);
        return result.getProds();
    }

    public List<Customer> loadInitialDataCustomersFromMocks() {
        Customer[] result = restTemplate.getForObject(
                URL_LIST_MOCK_CUSTOMERS,
                Customer[].class);
        return Arrays.asList(result);
    }

    public Product requestExtraStockProduct(int extraAmount) {
        String URL = (extraAmount == 5) ? URL_MOCK_EXTRA_PRODUCT_5 : URL_MOCK_EXTRA_PRODUCT_10;
        Product result = restTemplate.getForObject(
                URL,
                Product.class);
        return result;
    }
}
