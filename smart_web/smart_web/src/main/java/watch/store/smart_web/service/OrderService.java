package watch.store.smart_web.service;


import watch.store.smart_web.dto.request.orders.OrdersRequest;
import watch.store.smart_web.dto.respone.VPResponDto;
import watch.store.smart_web.dto.respone.cart.CartRespone;
import watch.store.smart_web.dto.respone.order.OrderRespone;
import watch.store.smart_web.entity.OrdersEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface OrderService {

    String createOrder(OrdersRequest request);

    VPResponDto createOrderOnline (OrdersRequest request , HttpServletRequest requesthttp);

    String updateOrder(OrdersRequest request);

    String deleteOrder(long id);

    List<OrderRespone> findAllOrder();

    List<CartRespone> findByOrderDetail(String id);

    OrdersEntity findByOrder(String id);

    String canncelOrder(String id);

    String datLai(String id);

    List<OrderRespone> findByemail();


}
