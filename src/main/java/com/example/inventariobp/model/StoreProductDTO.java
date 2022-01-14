package com.example.inventariobp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@ApiModel("StoreProduct entity")
@Table(name = "StoreProduct")
public class StoreProductDTO {
    @Id
    @ApiModelProperty("StoreProduct ID")
    @Column(name = "id")
    private Long id;

    @ApiModelProperty("Store identification")
    @Column(name = "store_id")
    private Long stroreId;

    @ApiModelProperty("Product identification")
    @Column(name = "product_id")
    private Long productId;
}
