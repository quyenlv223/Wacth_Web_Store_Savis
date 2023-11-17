package watch.store.smart_web.service;

import watch.store.smart_web.dto.request.cart.CartEditRequest;
import watch.store.smart_web.dto.request.cart.CartRequest;
import watch.store.smart_web.dto.respone.cart.CartRespone;

import java.util.List;

public interface CartService {


    List<CartRespone> findByCustomer();

    String addCart(CartRequest cartRequest);

    String deleteCart(Long id);

    String updateCart(List<CartEditRequest> list);
}
