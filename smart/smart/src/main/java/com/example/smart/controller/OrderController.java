package com.example.smart.controller;

import com.example.smart.dto.respone.order.OrderRespone;
import com.example.smart.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;
    @GetMapping("/orders")
    public String index(Model model){
        List<OrderRespone> respones = orderService.findAllOrder();
        model.addAttribute("list", respones);
        return "views/orders/007_Orders_payment";

    }
}






