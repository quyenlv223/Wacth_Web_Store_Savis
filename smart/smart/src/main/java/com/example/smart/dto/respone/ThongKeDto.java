package com.example.smart.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThongKeDto {
    private String img;
    private String nameProduct;
    private String romProduct;
    private String colorProduct;

    private String priceProduct;
    private String quantityDaBan;

    private String totalPrice;
    private String dateOrder;
}
