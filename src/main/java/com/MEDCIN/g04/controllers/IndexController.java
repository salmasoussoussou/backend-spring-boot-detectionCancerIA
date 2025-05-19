// com.MEDCIN.g04.controllers.IndexController.java
package com.MEDCIN.g04.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // affiche login.html
    }

    @GetMapping("/index")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String roleMessage = "";

        if (auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"))) {
            roleMessage = "Vous êtes connecté en tant qu'ADMIN.";
        } else if (auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("MEDECIN"))) {
            roleMessage = "Vous êtes connecté en tant que MEDECIN.";
        }

        model.addAttribute("message", roleMessage);
        return "index"; // affiche index.html
    }
}
