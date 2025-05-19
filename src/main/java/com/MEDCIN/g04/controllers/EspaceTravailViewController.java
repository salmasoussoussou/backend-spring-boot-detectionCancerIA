package com.MEDCIN.g04.controllers;


import com.MEDCIN.g04.models.EspaceTravail;
import com.MEDCIN.g04.services.EspaceTravailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/espaces-view")
public class EspaceTravailViewController {
    private final EspaceTravailService espacetravailService;

    @Autowired

    public EspaceTravailViewController(EspaceTravailService espacetravailService) {
        this.espacetravailService = espacetravailService;
    }

    @GetMapping
    public String listEspaceTravail(Model model){
        List<EspaceTravail> espaces = espacetravailService.getAllEspaces();
        model.addAttribute("espaces", espaces);
        return "espaces/list";
    }
}
