package com.example.inventariobp.service;

import com.example.inventariobp.model.Product;
import com.example.inventariobp.model.dto.ProductDTO;
import com.example.inventariobp.repository.interfaces.IProductRepository;
import com.example.inventariobp.service.interfaces.IProductService;
import com.example.inventariobp.utils.Helpers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> listDTO = productRepository.findAll();

        List<ProductDTO> result = listDTO.stream()
                .map(element -> Helpers.modelMapper().map(element, ProductDTO.class))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public Product saveProduct(Product dto) {
        return productRepository.save(dto);
    }

    @Override
    public Product updateStockProduct(Long id, Double stock) {
        if (stock < 1)
            throw new IllegalStateException("The stock cannot be less than 1. stock = ".concat(String.valueOf(stock)));

        Optional<Product> result = productRepository.findById(id);
        result.get().setStock(stock);
        return productRepository.save(result.get());
    }

    @Override
    public Long deleteProduct(Long id) {
        productRepository.deleteById(id);
        return id;
    }
}
