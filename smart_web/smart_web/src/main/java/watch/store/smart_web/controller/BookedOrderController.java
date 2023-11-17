package watch.store.smart_web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import watch.store.smart_web.dto.respone.cart.CartRespone;
import watch.store.smart_web.dto.respone.order.OrderRespone;
import watch.store.smart_web.entity.OrdersEntity;
import watch.store.smart_web.entity.VoucherEntity;
import watch.store.smart_web.repo.VoucherRepo;
import watch.store.smart_web.service.OrderService;
import watch.store.smart_web.util.ConvertUtil;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("your-orders")
public class BookedOrderController {
    private final OrderService service;

    private final ConvertUtil convertUtil;

    private final VoucherRepo voucherRepo;

    @GetMapping("")
    public String index(Model model){
        List<OrderRespone> list = service.findByemail();
        model.addAttribute("listCart", list);
        return "/views/orders/order";
    }

    @GetMapping("/orders/{id}")
    public String index(@PathVariable String id, Model model){
        List<CartRespone> list = service.findByOrderDetail(id);
        OrdersEntity entity = service.findByOrder(id);
        VoucherEntity voucherEntity = entity.getVoucherEntity();
        long khuyenMai = 0;
        long tong = 0;
        for (CartRespone cart: list
        ) {
            tong+=cart.getTotal();
        }
        model.addAttribute("tong1", convertUtil.moneyToStringFormat(tong));
        if(voucherEntity != null){
            if(voucherEntity.getTypeDiscount().equals("đ")){
                khuyenMai = voucherEntity.getDiscount();
                tong -= khuyenMai;
            }else {
                khuyenMai = tong / 100 * voucherEntity.getDiscount();
                tong -= khuyenMai;
            }
        }
        model.addAttribute("id", id);
        model.addAttribute("tong", convertUtil.moneyToStringFormat(tong));
        model.addAttribute("voucher", convertUtil.moneyToStringFormat(khuyenMai));
        model.addAttribute("check", entity.getStatus());
        model.addAttribute("listCart", list);
        return "/views/orders/order-detail";
    }
}
