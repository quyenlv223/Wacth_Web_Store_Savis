package watch.store.smart_web.dto.respone.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRespone {

    private String id;
    private String nameProduct;
    private String idProduct;
    private long priceProduct;
    private String priceProductString;
    private Long quantity;
    private Long quantityProduct;
    private String color;
    private String rom;
    private String imgProduct;
    private long total;
    private String totalString;
    private long priceProductPromotion;
    private String priceProductPromotionString;
}
