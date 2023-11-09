package com.senior.hr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/testing")
    public String testing(){
        return "success";
    }
}
