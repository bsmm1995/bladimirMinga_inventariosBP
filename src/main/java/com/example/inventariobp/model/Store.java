package com.example.inventariobp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Store entity")
@Table(name = "Store")
public class Store {

    @Id
    @ApiModelProperty("Store ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Generator")
    @Column(name = "id", nullable = false)
    private Long id;

    @ApiModelProperty("Store code")
    private String cod;

    @ApiModelProperty("Store name")
    private String name;

    @ApiModelProperty("Store name")
    @Column(name = "is_main")
    private Boolean isMain;

    public Store(String cod, String name, Boolean isMain) {
        this.cod = cod;
        this.name = name;
        this.isMain = isMain;
    }
}
