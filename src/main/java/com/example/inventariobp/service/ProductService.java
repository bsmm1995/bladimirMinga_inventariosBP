package com.example.inventariobp.service;

import com.example.inventariobp.model.ProductDTO;
import com.example.inventariobp.repository.IProductRepository;
import com.example.inventariobp.service.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    @Override
    public Optional<ProductDTO> getProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductDTO saveProduct(ProductDTO dto) {
        return productRepository.save(dto);
    }
    
    @Override
    public Long deleteProduct(Long id) {
        productRepository.deleteById(id);
        return id;
    }
}
