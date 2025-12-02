package com.springboot.chatgpt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thietke")
public class InteriorController {

    @GetMapping("/chatgpt")
    public String thietKeForm() {
        return "thietke/ketqua";
    }
}
