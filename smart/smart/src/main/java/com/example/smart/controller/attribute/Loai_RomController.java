package com.example.smart.controller.attribute;


import com.example.smart.service.ILoaiRomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("loairom")
public class Loai_RomController {
    private final ILoaiRomService service;

    @GetMapping
    public String index(Model model){
        model.addAttribute("list", service.findAll());

        return "/views/product/attribute/loai_rom";
    }
}
