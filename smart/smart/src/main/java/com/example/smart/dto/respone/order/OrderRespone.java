package com.example.smart.dto.respone.order;

import com.example.smart.dto.respone.customer.CustomerRespone;
import com.example.smart.dto.respone.order_detail.OrderDetailRespone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRespone {
    private String codeOrder;
    private String totalString;
    private String total;
    private String orderCreate;
    private String typeOrder;
    private String customerName;
    private String shipping;
    private CustomerRespone customerRespone;
    private List<OrderDetailRespone> listDetail;
    private String address;
    private String deliveryDate;
    private String shipperName;
    private String shipperPhone;
    private String note;
    private String voucherName;
    private String orderType;
   }
// shipping là trạng thái đơn hàng : 0 = chờ xác nhận , 1 = chờ xuất hàng, 2 = chờ giao hàng, 3 = đang giao hàng, 4 = hoàn thành, -1 = đơn hảng bị hủy
