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
@ApiModel("Customer entity")
@Table(name = "Customer")
public class CustomerDTO {
    @Id
    @ApiModelProperty("Customer ID")
    @Column(name = "id", nullable = false)
    private Long id;

    @ApiModelProperty("Customer name")
    private String name;

    @ApiModelProperty("Customer identification")
    private String dni;

    @ApiModelProperty("Customer picture")
    private String picture;
}