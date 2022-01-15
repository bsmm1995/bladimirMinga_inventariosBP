package com.example.inventariobp.config;

import com.example.inventariobp.model.ProductDTO;
import com.example.inventariobp.model.vo.ProductsMockVO;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ManageMock {

    private RestTemplate restTemplate;

    private final String URL_LIST_MOCK_PRODUCTS = "https://mocki.io/v1/1e10940d-1c04-42a4-9aff-d8dbe9a683ae";
    //private final String URL_LIST_MOCK_PRODUCTS = "https://mocki.io/v1/346eb340-1076-46b1-9e35-22d44913b542"; sin id
    private final String URL_MOCK_EXTRA_PRODUCT_10 = "https://mocki.io/v1/62c92dbb-b16e-49ec-9a01-13be552b5c22";
    private final String URL_MOCK_EXTRA_PRODUCT_5 = "https://mocki.io/v1/e6a68ea6-d70e-490d-ad40-bedb338e8076";

    public List<ProductDTO> loadInitialDataProductsFromMocks() {
        restTemplate = new RestTemplate();
        ProductsMockVO result = restTemplate.getForObject(
                URL_LIST_MOCK_PRODUCTS,
                ProductsMockVO.class);
        return result.getProds();
    }

    public ProductDTO requestExtraStock(int extraAmount) {
        restTemplate = new RestTemplate();
        String URL = (extraAmount == 5) ? URL_MOCK_EXTRA_PRODUCT_5 : URL_MOCK_EXTRA_PRODUCT_10;
        ProductDTO result = restTemplate.getForObject(
                URL,
                ProductDTO.class);
        return result;
    }
}
