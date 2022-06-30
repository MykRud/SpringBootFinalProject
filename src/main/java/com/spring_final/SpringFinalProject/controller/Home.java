package com.spring_final.SpringFinalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
    @RequestMapping("/home")
    public String getHome(){
        return "index";
    }

}
