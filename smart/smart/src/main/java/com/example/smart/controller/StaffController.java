package com.example.smart.controller;

import com.example.smart.dto.respone.staff.StaffResponeDto;
import com.example.smart.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("staff")
public class StaffController {
    @Autowired
    private IStaffService staffService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public String index(Model model) {
        List<StaffResponeDto> staffs = staffService.findAllByDeleteFlagIsFalse();
        model.addAttribute("staffs", staffs);

        return "views/staff/001_Staff";
    }
}
