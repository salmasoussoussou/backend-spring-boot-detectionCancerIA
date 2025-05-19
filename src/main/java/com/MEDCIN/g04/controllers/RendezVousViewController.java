package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.models.RendezVous;
import com.MEDCIN.g04.services.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/rendezvous-view") // évite le conflit avec API
public class RendezVousViewController {

    private final RendezVousService rendezvousService;

    @Autowired
    public RendezVousViewController(RendezVousService rendezvousService) {
        this.rendezvousService = rendezvousService;
    }

    @GetMapping
    public String listRendezVous(Model model) {
        List<RendezVous> rendezvousList = rendezvousService.getAllRendezVous();
        model.addAttribute("rendezvousList", rendezvousList); // meilleur nom
        return "rendezvous/list";
    }
}
