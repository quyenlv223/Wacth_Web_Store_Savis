package com.example.smart.dto.request.orderinvoicedetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInvoiceDetailRequest {
    private Long moneyInvoice;
    private Long quantityInvoice;
    private Integer romId;
    private Integer colorId;
    private String status;
    private String note;
}
