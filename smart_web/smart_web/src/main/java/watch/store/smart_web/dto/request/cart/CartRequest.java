package watch.store.smart_web.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private String id;
    private String idProduct;
    private String quantityCart;
}
