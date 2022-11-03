package com.javahomeworkone;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {

    @GetMapping("")
    public String showHmonePage(){
        return "index";
    }
}
