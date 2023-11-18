package com.example.smart.controller.attribute;


import com.example.smart.service.ILoaiRomService;
import com.example.smart.service.IRomValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/romvalue")
public class Rom_ValueController {
    private final IRomValueService service;
    private final ILoaiRomService service1;



    @GetMapping
    public String index(Model model){
        model.addAttribute("list", service.findAll());
        model.addAttribute("listrom", service1.findAll());
        System.out.println(service1.findAll().get(0));
        return "/views/product/attribute/rom_value";
    }
}
