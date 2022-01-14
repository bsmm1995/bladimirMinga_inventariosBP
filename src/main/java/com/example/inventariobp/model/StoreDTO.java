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
@ApiModel("Store entity")
@Table(name = "Store")
public class StoreDTO {
    @Id
    @ApiModelProperty("Store ID")
    @Column(name = "id", nullable = false)
    private Long id;

    @ApiModelProperty("Store code")
    private String cod;

    @ApiModelProperty("Store name")
    private String name;

    @ApiModelProperty("Store name")
    @Column(name = "is_main")
    private Boolean isMain;
}
