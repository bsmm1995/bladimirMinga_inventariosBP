package com.example.inventariobp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDetailVO {
    private Long transactionId;
    private Date date;
    private String store;
    private String productCode;
    private String productName;
    private Double price;
    private Double quantity;
    private Double total;
}