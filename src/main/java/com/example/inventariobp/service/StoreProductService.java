package com.example.inventariobp.service;

import com.example.inventariobp.data.Product;
import com.example.inventariobp.data.StoreProduct;
import com.example.inventariobp.repository.interfaces.IReportsRepository;
import com.example.inventariobp.repository.interfaces.IStoreProductRepository;
import com.example.inventariobp.service.interfaces.IStoreProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreProductService implements IStoreProductService {

    private final IStoreProductRepository storeProductRepository;
    private final IReportsRepository reportsRepository;

    @Override
    public Optional<StoreProduct> getStoreProduct(Long id) {
        return storeProductRepository.findById(id);
    }

    @Override
    public List<Product> getAllProductsByStore(Long storeID) {
        return reportsRepository.getAllProductsByStore(storeID);
    }

    @Override
    public StoreProduct saveStoreProduct(StoreProduct dto) {
        return storeProductRepository.save(dto);
    }

    @Override
    public Long deleteStoreProduct(Long id) {
        storeProductRepository.deleteById(id);
        return id;
    }
}
