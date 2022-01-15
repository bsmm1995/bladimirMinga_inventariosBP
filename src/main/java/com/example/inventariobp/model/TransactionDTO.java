package com.example.inventariobp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@ApiModel("Transaction entity")
@Table(name = "Transaction")
public class TransactionDTO {

    @TableGenerator(name = "TransactionGenerator")

    @Id
    @ApiModelProperty("Transaction ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TransactionGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    @ApiModelProperty("Store identification")
    @Column(name = "store_id")
    private Long storeId;

    @ApiModelProperty("Customer identification")
    @Column(name = "customer_id")
    private Long customerId;

    @ApiModelProperty("Transaction date")
    private Date date;

    @ApiModelProperty("Transaction total")
    private Double total;

    @Transient
    private List<TransactionDetailDTO> detail;

    public TransactionDTO() {
        detail = new ArrayList<>();
        total = 0.0;
    }
}
