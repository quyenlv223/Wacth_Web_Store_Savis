package com.example.smart.dto.request.voucher;

import com.example.smart.constant.ConstansErrorCode;
import com.example.smart.constant.ContainsFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoucherRequest {

    private String voucherId;

    @NotNull(message = ConstansErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstansErrorCode.VOUCHER_CODE_BLANK)
    private String voucherCode;

    @NotNull(message = ConstansErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstansErrorCode.VOUCHER_NAME_BLANK)
    private String voucherName;

    @NotNull(message = ConstansErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstansErrorCode.VOUCHER_START_DATE_BLANK)
    @Pattern(regexp = ContainsFormat.REGEX_DATE, message = ConstansErrorCode.DATE_NOT_FORMAT)
    private String voucherStartDate;

    @NotNull(message = ConstansErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstansErrorCode.VOUCHER_END_DATE_BLANK)
    @Pattern(regexp = ContainsFormat.REGEX_DATE, message = ConstansErrorCode.DATE_NOT_FORMAT)
    private String voucherEndDate;

    @NotNull(message = ConstansErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstansErrorCode.VOUCHER_DISCOUNT_BLANK)
    private String voucherDiscount;

    @NotNull(message = ConstansErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstansErrorCode.VOUCHER_DISCOUNT_BLANK)
    private String voucherAccompanyPromo;

    private String voucherMoneyMin;

    private String voucherQuantity;

    private String voucherDescription;

    private String voucherCategory;

    private String voucherStatus;

    private String note;

    private String voucherPersonApply;

    private String voucherTypeDiscount;

    private List<String> typeDiscountPerson;

    private String typeDiscountMoneyMin;

}
