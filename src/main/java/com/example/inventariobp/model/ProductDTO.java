package com.example.inventariobp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Product entity")
@Table(name = "Product")
public class ProductDTO {
    @Id
    @ApiModelProperty("Product ID")
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