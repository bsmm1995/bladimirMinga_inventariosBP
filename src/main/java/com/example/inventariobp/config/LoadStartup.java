package com.example.inventariobp.config;

import com.example.inventariobp.model.ProductDTO;
import com.example.inventariobp.model.vo.ProductsMockVO;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class LoadStartup {

    private final String URL_LIST_MOCK_PRODUCTS = "https://mocki.io/v1/1e10940d-1c04-42a4-9aff-d8dbe9a683ae";

    public List<ProductDTO> loadInitialDataProductsFromMocks() throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        ProductsMockVO result = restTemplate.getForObject(
                URL_LIST_MOCK_PRODUCTS,
                ProductsMockVO.class);
        return result.getProds();
    }
}
