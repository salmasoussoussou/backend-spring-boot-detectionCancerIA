package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.models.Secretaire;
import com.MEDCIN.g04.services.SecretaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/secretaires-view")
public class SecretaireViewController {
    private SecretaireService secretaireService;

    @Autowired
    public SecretaireViewController(SecretaireService secretaireService) {
        this.secretaireService = secretaireService;
    }
    @GetMapping
    public String listSecretaire(Model model) {
        List<Secretaire> secretairesList = secretaireService.getAllSecretaires();
        model.addAttribute("secretairesList", secretairesList); // meilleur nom
        return "secretaires/list";
    }
}
