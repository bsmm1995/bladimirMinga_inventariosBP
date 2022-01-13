package com.example.inventariobp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Transaction entity")
@Table(name = "Transaction")
public class TransactionDTO {
    @Id
    @ApiModelProperty("Transaction ID")
    @Column(name = "id")
    private Long id;

    @ApiModelProperty("Store identification")
    @Column(name = "store_id")
    private Long stroreId;

    @ApiModelProperty("Customer identification")
    @Column(name = "customer_id")
    private Long customerId;

    @ApiModelProperty("Transaction date")
    private Date date;

    @ApiModelProperty("Transaction total")
    private Double total;
}
