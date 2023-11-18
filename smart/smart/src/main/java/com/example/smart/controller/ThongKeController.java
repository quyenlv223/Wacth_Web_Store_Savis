package com.example.smart.controller;

import com.example.smart.dto.respone.ThongKeDto;
import com.example.smart.repo.ThongKeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class ThongKeController {

    @Autowired
    private ThongKeRepo thongKeRepo;
    @GetMapping("/")
    public String index(Model model){
        try {
            List<ThongKeDto> thongKeDtos = thongKeRepo.findStockAkhirPerProductIn(12, 2022);
            return "/views/index";
        }catch (Exception e){
            e.printStackTrace();
            return "/views/index";
        }

    }
}
