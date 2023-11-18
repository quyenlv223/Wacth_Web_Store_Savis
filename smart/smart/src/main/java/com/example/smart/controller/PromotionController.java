package com.example.smart.controller;

import com.example.smart.dto.respone.product.ProductResponse;
import com.example.smart.dto.respone.promotion.PromotionResponseDTO;
import com.example.smart.service.IProductService;
import com.example.smart.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/promotion")
public class PromotionController {
    @Autowired
    private IPromotionService service;

    @Autowired
    private IProductService productService;


    @GetMapping()
    public String getAll(Model model){
        List<PromotionResponseDTO> promotionList = service.getAll();

        model.addAttribute("promotions", promotionList);
//
//        List<CategoryResponse> lstCategory = categoryService.getAllCategory();
//        model.addAttribute("categories", lstCategory);
//
        List<ProductResponse> lstProduct = productService.findAll();
        model.addAttribute("products", lstProduct);
        return "views/promotion/promotion.html";
    }
}
