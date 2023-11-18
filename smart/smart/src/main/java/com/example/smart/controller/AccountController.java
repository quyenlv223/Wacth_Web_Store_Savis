package com.example.smart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountController {


    @GetMapping("/login")
    public String login(){
        return "/views/loginV2";
    }
}
