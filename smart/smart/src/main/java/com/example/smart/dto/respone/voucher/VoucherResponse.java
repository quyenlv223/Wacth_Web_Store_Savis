package com.example.smart.dto.respone.voucher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoucherResponse {

    private String voucherId;

    private String voucherCode;

    private String voucherName;

    private Date voucherStartDate;

    private Date voucherEndDate;

    private Long voucherDiscount;

    private String voucherTypeDiscount;

    private Integer voucherMinAmount;

    private Integer voucherQuantity;

    private String voucherDescription;

    private String categoryId;

    private String voucherStatus;

    private Boolean voucherHidden;

    private Date voucherCreateDate;

    private String voucherCreateBy;

    private Date voucherModifiedDate;

    private Long typeDiscountMinAmount;

    private List<Long> lstCustomer;

    private String voucherKey;

}
