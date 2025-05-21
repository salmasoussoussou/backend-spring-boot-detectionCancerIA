package com.MEDCIN.g04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {



    @GetMapping("/medecin-dashboard")
    public String medecinDashboard() {
        return "medecin-dashboard"; // fichier HTML : medecin-dashboard.html
    }

    @GetMapping("/dashboard")
    public String defaultDashboard() {
        return "dashboard"; // fichier HTML par défaut (facultatif)
    }
}
