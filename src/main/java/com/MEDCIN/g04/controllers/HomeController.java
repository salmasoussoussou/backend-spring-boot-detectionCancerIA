package com.MEDCIN.g04.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String index() {
        return "home"; // Va chercher home.html dans templates
    }
}
