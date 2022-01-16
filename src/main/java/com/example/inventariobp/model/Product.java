package com.example.inventariobp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Product entity")
@Table(name = "Product")
public class Product {

    @Id
    @ApiModelProperty("Product ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Generator")
    @Column(name = "id", nullable = false)
    private Long id;

    @ApiModelProperty("Product code")
    private String cod;

    @ApiModelProperty("Product name")
    private String name;

    @ApiModelProperty("Product price")
    private Double price;

    @ApiModelProperty("Product stock")
    private Double stock;
}