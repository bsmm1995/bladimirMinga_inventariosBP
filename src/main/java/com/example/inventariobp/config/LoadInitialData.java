package com.example.inventariobp.config;

import com.example.inventariobp.data.*;
import com.example.inventariobp.repository.interfaces.*;
import com.example.inventariobp.service.complementary.ManageMock;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.persistence.TableGenerator;
import java.util.List;

@Configuration
public class LoadInitialData {

    private final ManageMock manageMock;

    public LoadInitialData() {
        this.manageMock = new ManageMock();
    }

    @TableGenerator(name = "Generator")

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CommandLineRunner initProducts(IProductRepository productRepository) {

        return args -> {
            List<Product> result = manageMock.loadInitialDataProductsFromMocks();
            result.forEach(e -> productRepository.save(e));
        };
    }

    @Bean
    CommandLineRunner initCustomers(ICustomerRepository customerRepository) {

        return args -> {
            List<Customer> result = manageMock.loadInitialDataCustomersFromMocks();
            result.forEach(e -> customerRepository.save(e));
        };
    }

    @Bean
    CommandLineRunner initTransactions(ITransactionRepository transactionRepository) {

        return args -> {
            List<Transaction> result = manageMock.loadInitialDataTransactionsFromMocks();
            result.forEach(e -> transactionRepository.save(e));
        };
    }

    @Bean
    CommandLineRunner initTransactionsDetail(ITransactionDetailRepository transactionDetailRepository) {

        return args -> {
            List<TransactionDetail> result = manageMock.loadInitialDataTransactionsDetailFromMocks();
            result.forEach(e -> transactionDetailRepository.save(e));
        };
    }

    @Bean
    CommandLineRunner initStoreProducts(IStoreProductRepository storeProductRepository) {

        return args -> {
            List<StoreProduct> result = manageMock.loadInitialDataStoreProductsFromMocks();
            result.forEach(e -> storeProductRepository.save(e));
        };
    }
}
