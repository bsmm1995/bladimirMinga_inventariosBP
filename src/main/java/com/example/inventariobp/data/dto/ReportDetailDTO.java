package com.example.inventariobp.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDetailDTO {
    private Long transactionId;
    private Date date;
    private String store;
    private String productCode;
    private String productName;
    private Double price;
    private Double quantity;
    private Double total;
}