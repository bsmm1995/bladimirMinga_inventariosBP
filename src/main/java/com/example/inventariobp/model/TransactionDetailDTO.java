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
@ApiModel("Transaction detail entity")
@Table(name = "Transaction_Detail")
public class TransactionDetailDTO {

    @TableGenerator(name = "Transaction_DetailGenerator")

    @Id
    @ApiModelProperty("Transaction detail ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Transaction_DetailGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    @ApiModelProperty("Transaction identification")
    @Column(name = "transaction_id")
    private Long transactionId;

    @ApiModelProperty("Product identification")
    @Column(name = "product_id")
    private Long productId;

    @ApiModelProperty("Product quantity")
    private Double quantity;

    @ApiModelProperty("Price of the product")
    private Double price;
}
