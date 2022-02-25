package com.example.inventariobp.model.dto;

import com.example.inventariobp.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductsMockDTO {
    private List<Product> prods;
}