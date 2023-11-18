package com.example.smart.dto.request.order;

import com.example.smart.dto.request.orderdetail.OrderDetailRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String id;
    private String totalMoney;
    private List<OrderDetailRequest> detailRequest;
    private String recipientAddress; //địa chỉ giao hàng
    private Date deliveryDate; // ngày giao hàng
    private String shipperName;
    private String note;
    private String shipperPhone;
    private String fullName;
    private String phoneNumber;
    private String orderType;
    private String voucherId;


}
