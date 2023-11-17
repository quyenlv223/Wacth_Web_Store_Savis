package watch.store.smart_web.intercepter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import watch.store.smart_web.dto.respone.cart.CartRespone;
import watch.store.smart_web.service.CartService;
import watch.store.smart_web.service.CategoryService;
import watch.store.smart_web.util.ConvertUtil;
import watch.store.smart_web.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class CartIntercepter implements HandlerInterceptor {
    private final SessionUtil sessionUtil;

    private final CartService cartService;

    private final ConvertUtil convertUtil;

    private final CategoryService categoryService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long totalCart = 0;
        int sizeCart = 0;
        if(String.valueOf(sessionUtil.getObject("username")) != null){
            List<CartRespone> listCart = cartService.findByCustomer();
            sizeCart = listCart.size();
            for (CartRespone respone: listCart
                 ) {
                totalCart+= respone.getTotal();
            }
        }
        request.setAttribute("sizeCart", sizeCart);
        request.setAttribute("category", categoryService.findByCategoryAndDeleteFlagIsFalse());
        request.setAttribute("totalCart", convertUtil.moneyToStringFormat(totalCart));
        request.setAttribute("totalCartPrice", totalCart);
        return true;
    }
}
