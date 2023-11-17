package watch.store.smart_web.service;

import watch.store.smart_web.dto.respone.attribute.AttributeRespone;


public interface AttributeService {
    AttributeRespone findByProduct(Long id);
}
