package watch.store.smart_web.dto.respone.voucher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherRespone {
    private String type;
    private String discount;
    private String quantity;
    private String accompanyPromo;
    private int minAmount;
    private Long typeDiscountMoneyMin;
}
