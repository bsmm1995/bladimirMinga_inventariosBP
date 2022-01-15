package com.example.inventariobp.service;

import com.example.inventariobp.model.*;
import com.example.inventariobp.model.vo.OrderVO;
import com.example.inventariobp.repository.ICustomerRepository;
import com.example.inventariobp.repository.IStoreRepository;
import com.example.inventariobp.service.interfaces.IProductService;
import com.example.inventariobp.service.interfaces.IStoreService;
import com.example.inventariobp.service.interfaces.ITransactionDetailService;
import com.example.inventariobp.service.interfaces.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreService implements IStoreService {

    private final IStoreRepository storeRepository;
    private final ICustomerRepository customerRepository;
    private final IProductService productService;
    private final ITransactionService transactionService;
    private final ITransactionDetailService transactionDetailService;
    private final EntityManager entityManager;

    @Override
    public Optional<StoreDTO> getStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public StoreDTO saveStore(StoreDTO dto) {
        return storeRepository.save(dto);
    }

    @Override
    public Long deleteStore(Long id) {
        storeRepository.deleteById(id);
        return id;
    }

    @Override
    public Boolean placeOrder(OrderVO data) {
        Optional<CustomerDTO> customer = customerRepository.findById(data.getCustomerId());
        if (!customer.isPresent())
            throw new IllegalStateException(String.format("Client with id %d does not exist", data.getCustomerId()));

        List<TransactionDTO> transactionList = new ArrayList<>();

        //Here you can save in a list all transactions with their details. If an exception occurs, no DB record is altered
        data.getDetail().forEach(element -> {
            Optional<StoreDTO> store = storeRepository.findById(element.getStoreId());
            if (!store.isPresent())
                throw new IllegalStateException(String.format("Store with id %d does not exist", element.getStoreId()));

            TransactionDTO newTransaction = new TransactionDTO();
            newTransaction.setCustomerId(customer.get().getId());
            newTransaction.setStoreId(store.get().getId());
            newTransaction.setDate(new Date());

            element.getDetail().forEach(e -> {
                Optional<ProductDTO> product = productService.getProduct(e.getProductId());
                if (!product.isPresent())
                    throw new IllegalStateException(String.format("Product with id %d does not exist", e.getProductId()));

                if (!store.get().getIsMain() && !productExistsInTheStore(store.get().getId(), product.get().getId()))
                    throw new IllegalStateException(String.format("Product %s is not sold in store %s", product.get().getName(), store.get().getName()));

                if (e.getQuantity() == null || Math.abs(e.getQuantity()) < Double.MIN_NORMAL)
                    throw new IllegalStateException(String.format("The quantity of the product %s cannot be less than one unit", product.get().getName()));

                if (product.get().getStock() - e.getQuantity() < -10)
                    throw new IllegalStateException(String.format("Unavailable units of product %s (> 10)", product.get().getName()));

                TransactionDetailDTO transactionDetail = new TransactionDetailDTO();
                transactionDetail.setProductId(e.getProductId());
                transactionDetail.setPrice(product.get().getPrice());
                transactionDetail.setQuantity(e.getQuantity());
                newTransaction.getDetail().add(transactionDetail);
                newTransaction.setTotal(newTransaction.getTotal() + e.getQuantity() * product.get().getPrice());
            });

            transactionList.add(newTransaction);
        });

        //Here you can store the transactions with their respective details and you can request extra stock.
        transactionList.forEach(element -> {
            TransactionDTO transactionSaved = transactionService.saveTransaction(element);
            element.getDetail().forEach(e -> {
                e.setTransactionId(transactionSaved.getId());
                Optional<ProductDTO> product = productService.getProduct(e.getProductId());
                if (product.get().getStock() - e.getQuantity() < -5) {
                    ProductDTO extraProduct = new ManageMock().requestExtraStockProduct(10);
                    product.get().setStock(product.get().getStock() + extraProduct.getStock());
                    productService.updateStockProduct(product.get().getId(), product.get().getStock());
                }

                if (product.get().getStock() - e.getQuantity() < 1 && product.get().getStock() - e.getQuantity() > -6) {
                    new Thread(() -> {
                        ProductDTO extraProduct = new ManageMock().requestExtraStockProduct(5);
                        product.get().setStock(product.get().getStock() + extraProduct.getStock() - e.getQuantity());
                        productService.updateStockProduct(product.get().getId(), product.get().getStock());
                    }).start();
                } else {
                    transactionDetailService.saveTransactionDetail(e);
                    productService.updateStockProduct(product.get().getId(), product.get().getStock() - e.getQuantity());
                }
            });
        });

        return Boolean.TRUE;
    }

    @Override
    public Boolean productExistsInTheStore(Long storeId, Long productId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT 1 FROM STORE_PRODUCT ")
                .append("WHERE ")
                .append("PRODUCT_ID = :productId ")
                .append("AND STORE_ID = :storeId ");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("productId", productId);
        query.setParameter("storeId", storeId);

        return query.getResultList().size() > 0;
    }
}