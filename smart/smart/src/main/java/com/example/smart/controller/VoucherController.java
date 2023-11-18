package com.example.smart.controller;

import com.example.smart.dto.respone.category.CategoryResponeDto;
import com.example.smart.dto.respone.voucher.VoucherResponse;
import com.example.smart.service.ICategoryService;
import com.example.smart.service.IVoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/voucher")
@RequiredArgsConstructor
public class VoucherController {

    private final IVoucherService voucherService;

    private final ICategoryService categoryService;

    //private final CustomerService customerService;

    //private final OrderService orderService;

    @GetMapping
    public String voucherManager(Model model) {
        List<VoucherResponse> lstVoucher = voucherService.getAllVoucher();
//        for (VoucherResponse x : lstVoucher) {
//            x.setLstCustomer(voucherService.findAllIdCustomerByVoucherId(x.getVoucherId()));
//        }
        List<CategoryResponeDto> lstCategory = categoryService.getAllCategory();
       // List<CustomerInfoResponse> lstCustomer = customerService.getCustomers();
        lstVoucher.sort(((o1, o2) -> o2.getVoucherCreateDate().compareTo(o1.getVoucherCreateDate())));
        model.addAttribute("lstVoucher", lstVoucher);
        model.addAttribute("lstCategory", lstCategory);
       // model.addAttribute("lstCustomer", lstCustomer);
        return "views/voucher/index";
    }

}
