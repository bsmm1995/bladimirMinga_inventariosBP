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
@ApiModel("Customer entity")
@Table(name = "Customer")
public class CustomerDTO {

    @Id
    @ApiModelProperty("Customer ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Generator")
    @Column(name = "id", nullable = false)
    private Long id;

    @ApiModelProperty("Customer name")
    @Column(name = "name", nullable = false)
    private String name;

    @ApiModelProperty("Customer identification")
    @Column(name = "dni", nullable = false)
    private String dni;

    @ApiModelProperty("Customer picture")
    @Column(name = "picture", columnDefinition = "TEXT")
    private String picture;
}