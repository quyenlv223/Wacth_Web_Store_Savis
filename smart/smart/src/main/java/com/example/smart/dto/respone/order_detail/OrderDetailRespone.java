package com.example.smart.dto.respone.order_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRespone {
    private String idDetail;
    private String idProductProperty;
    private String imageProduct;
    private String nameProduct;
    private String romProduct;
    private String colorProduct;
    private String quantity;
    private String tonKho;
    private String giaBanString;
    private long giaBan;
    private String tongTienString;
    private String statusProduct;
    private long tongTien;
}
