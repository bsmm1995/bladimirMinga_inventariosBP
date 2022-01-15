package com.example.inventariobp.controller.config;

import com.example.inventariobp.model.*;
import com.example.inventariobp.repository.*;
import com.example.inventariobp.service.ManageMock;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.persistence.TableGenerator;
import java.util.List;

@Configuration
public class LoadInitialData {

    @TableGenerator(name = "Generator")

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CommandLineRunner initProducts(IProductRepository productRepository) {

        return args -> {
            List<ProductDTO> result = new ManageMock().loadInitialDataProductsFromMocks();
            result.forEach(e -> productRepository.save(e));
        };
    }

    @Bean
    CommandLineRunner initCustomers(ICustomerRepository customerRepository) {

        return args -> {
            List<CustomerDTO> result = new ManageMock().loadInitialDataCustomersFromMocks();
            result.forEach(e -> customerRepository.save(e));
        };
    }

    @Bean
    CommandLineRunner initTransactions(ITransactionRepository transactionRepository) {

        return args -> {
            List<TransactionDTO> result = new ManageMock().loadInitialDataTransactionsFromMocks();
            result.forEach(e -> transactionRepository.save(e));
        };
    }

    @Bean
    CommandLineRunner initTransactionsDetail(ITransactionDetailRepository transactionDetailRepository) {

        return args -> {
            List<TransactionDetailDTO> result = new ManageMock().loadInitialDataTransactionsDetailFromMocks();
            result.forEach(e -> transactionDetailRepository.save(e));
        };
    }

    @Bean
    CommandLineRunner initStoreProducts(IStoreProductRepository storeProductRepository) {

        return args -> {
            List<StoreProductDTO> result = new ManageMock().loadInitialDataStoreProductsFromMocks();
            result.forEach(e -> storeProductRepository.save(e));
        };
    }
}
