package com.example.smart.controller;

import com.example.smart.dto.respone.category.CategoryResponeDto;
import com.example.smart.service.ICateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("cate")
public class CateController {
    private final ICateService service;




    @GetMapping
    public String index(Model model){
        List<CategoryResponeDto> list = service.findAll();
        model.addAttribute("listcategory", list);
        return "views/cate/cate";
    }
}
