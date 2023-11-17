package watch.store.smart_web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import watch.store.smart_web.dto.respone.cart.CartRespone;
import watch.store.smart_web.service.CartService;
import watch.store.smart_web.util.ConvertUtil;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    private final ConvertUtil convertUtil;



    @GetMapping("")
    public String index(Model model){
        List<CartRespone> list = cartService.findByCustomer();
        long tong = 0;
        for (CartRespone cart: list
        ) {
            tong+=cart.getTotal();
        }
        model.addAttribute("listCart", list);
        model.addAttribute("tong", convertUtil.moneyToStringFormat(tong));
        return "views/cart/cart";
    }
}
