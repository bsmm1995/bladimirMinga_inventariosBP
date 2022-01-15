package com.example.inventariobp.service;

import com.example.inventariobp.model.*;
import com.example.inventariobp.model.vo.ProductsMockVO;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ManageMock {

    private RestTemplate restTemplate;
    private final String URL_LIST_MOCK_TRANSACTIONS = "https://mocki.io/v1/0319d867-4f64-4106-9aaa-17d3bc932869";
    private final String URL_LIST_MOCK_TRANSACTIONS_DETAIL = "https://mocki.io/v1/9b54cba1-9930-4bfc-9b82-ca5c659e7df1";
    private final String URL_LIST_MOCK_STORE_PRODUCTS = "https://mocki.io/v1/70c40514-0124-4d24-be1b-8df8f2d7cfdc";

    private final String URL_LIST_MOCK_CUSTOMERS = "https://mocki.io/v1/993f9b88-6eab-4c5d-ab41-9989788340be";
    private final String URL_LIST_MOCK_PRODUCTS = "https://mocki.io/v1/1e10940d-1c04-42a4-9aff-d8dbe9a683ae";

    private final String URL_MOCK_EXTRA_PRODUCT_10 = "https://mocki.io/v1/62c92dbb-b16e-49ec-9a01-13be552b5c22";
    private final String URL_MOCK_EXTRA_PRODUCT_5 = "https://mocki.io/v1/e6a68ea6-d70e-490d-ad40-bedb338e8076";

    public List<StoreProductDTO> loadInitialDataStoreProductsFromMocks() {
        restTemplate = new RestTemplate();
        StoreProductDTO[] result = restTemplate.getForObject(
                URL_LIST_MOCK_STORE_PRODUCTS,
                StoreProductDTO[].class);
        return Arrays.asList(result);
    }

    public List<TransactionDetailDTO> loadInitialDataTransactionsDetailFromMocks() {
        restTemplate = new RestTemplate();
        TransactionDetailDTO[] result = restTemplate.getForObject(
                URL_LIST_MOCK_TRANSACTIONS_DETAIL,
                TransactionDetailDTO[].class);
        return Arrays.asList(result);
    }

    public List<TransactionDTO> loadInitialDataTransactionsFromMocks() {
        restTemplate = new RestTemplate();
        TransactionDTO[] result = restTemplate.getForObject(
                URL_LIST_MOCK_TRANSACTIONS,
                TransactionDTO[].class);
        return Arrays.asList(result);
    }

    public List<ProductDTO> loadInitialDataProductsFromMocks() {
        restTemplate = new RestTemplate();
        ProductsMockVO result = restTemplate.getForObject(
                URL_LIST_MOCK_PRODUCTS,
                ProductsMockVO.class);
        return result.getProds();
    }

    public List<CustomerDTO> loadInitialDataCustomersFromMocks() {
        restTemplate = new RestTemplate();
        CustomerDTO[] result = restTemplate.getForObject(
                URL_LIST_MOCK_CUSTOMERS,
                CustomerDTO[].class);
        return Arrays.asList(result);
    }

    public ProductDTO requestExtraStockProduct(int extraAmount) {
        restTemplate = new RestTemplate();
        String URL = (extraAmount == 5) ? URL_MOCK_EXTRA_PRODUCT_5 : URL_MOCK_EXTRA_PRODUCT_10;
        ProductDTO result = restTemplate.getForObject(
                URL,
                ProductDTO.class);
        return result;
    }
}
