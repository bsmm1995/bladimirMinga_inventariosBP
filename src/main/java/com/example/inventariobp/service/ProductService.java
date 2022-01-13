package com.example.inventariobp.service;

import com.example.inventariobp.model.ProductDTO;
import com.example.inventariobp.model.vo.ProductVO;
import com.example.inventariobp.repository.IProductRepository;
import com.example.inventariobp.service.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final EntityManager entityManager;

    @Override
    public Optional<ProductDTO> getProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductVO> getAllProducts() {
        Query nativeQuery = entityManager.createNativeQuery("SELECT cod, name FROM Product");
        List<Object[]> results = nativeQuery.getResultList();
        return results
                .stream()
                .map(result -> new ProductVO((String) result[0], (String) result[1]))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO saveProduct(ProductDTO dto) {
        return productRepository.save(dto);
    }

    @Override
    public ProductDTO updateStockProduct(Long id, Double stock) {
        if (stock < 1)
            throw new IllegalStateException("The stock cannot be less than 1. stock = ".concat(String.valueOf(stock)));

        Optional<ProductDTO> result = productRepository.findById(id);
        ProductDTO update = result.get();
        update.setStock(stock);
        return productRepository.save(update);
    }

    @Override
    public Long deleteProduct(Long id) {
        productRepository.deleteById(id);
        return id;
    }
}
