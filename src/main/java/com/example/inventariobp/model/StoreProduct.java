package com.example.inventariobp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("StoreProduct entity")
@Table(name = "StoreProduct")
public class StoreProduct {

    @Id
    @ApiModelProperty("StoreProduct ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Generator")
    @Column(name = "id", nullable = false)
    private Long id;

    @ApiModelProperty("Store identification")
    @Column(name = "store_id")
    private Long storeId;

    @ApiModelProperty("Product identification")
    @Column(name = "product_id")
    private Long productId;
}
