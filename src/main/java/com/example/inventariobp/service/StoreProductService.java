package com.example.inventariobp.service;

import com.example.inventariobp.model.Product;
import com.example.inventariobp.model.StoreProduct;
import com.example.inventariobp.repository.interfaces.IReportsRepository;
import com.example.inventariobp.repository.interfaces.IStoreProductRepository;
import com.example.inventariobp.service.interfaces.IStoreProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
//        List<Product> results = reportsRepository.getAllProductsByStore(storeID);
//        return results
//                .stream()
//                .map(result -> new Product(((BigInteger) result[0]).longValue(), (String) result[1], (String) result[2], (Double) result[3], (Double) result[4]))
//                .collect(Collectors.toList());
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
