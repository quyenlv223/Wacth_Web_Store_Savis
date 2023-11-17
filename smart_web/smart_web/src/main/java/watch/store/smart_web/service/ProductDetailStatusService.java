package watch.store.smart_web.service;



import watch.store.smart_web.dto.respone.product.ProductPropertyRespone;

import java.util.List;

public interface ProductDetailStatusService {

    List<ProductPropertyRespone> findByRomId(Long romId);
}
