package com.example.inventariobp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Store entity")
@Table(name = "Store")
public class StoreDTO {

    @TableGenerator(name = "StoreGenerator")

    @Id
    @ApiModelProperty("Store ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "StoreGenerator")
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
