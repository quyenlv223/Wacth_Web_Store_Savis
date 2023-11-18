package com.example.smart.dto.request.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequest {
    private String id;
    private String productId;
    private String quantity;
    private String price;
}
