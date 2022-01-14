package com.example.inventariobp.config;

import com.example.inventariobp.model.ProductDTO;
import com.example.inventariobp.repository.IProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class GeneralSettings {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CommandLineRunner init(IProductRepository productRepository) {
        LoadStartup utilities = new LoadStartup();
        return args -> {
            List<ProductDTO> result = utilities.loadInitialDataProductsFromMocks();
            result.forEach(e -> productRepository.save(e));
        };
    }
}
