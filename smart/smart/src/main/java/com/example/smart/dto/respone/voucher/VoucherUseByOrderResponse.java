package com.example.smart.dto.respone.voucher;

import lombok.*;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoucherUseByOrderResponse {

    private Integer orderId;

    private String orderCode;

    private String voucherId;

    private String voucherCode;

    private String totalMoney;

    private String customerId;

    private String fullName;

    private String dateUsed;

}
