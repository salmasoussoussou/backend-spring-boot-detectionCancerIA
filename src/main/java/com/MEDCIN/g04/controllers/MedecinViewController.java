package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.models.Medecin;
import com.MEDCIN.g04.services.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/medecins-view")
public class MedecinViewController {

    private final MedecinService medecinService;

    @Autowired
    public MedecinViewController(MedecinService medecinService) {
        this.medecinService = medecinService;
    }

    // Afficher la liste de tous les médecins
    @GetMapping
    public String listMedecins(Model model) {
        List<Medecin> medecins = medecinService.getAllMedecins();
        model.addAttribute("medecins", medecins);
        return "medecins/list";
    }

    // Formulaire d'ajout d'un nouveau médecin
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("medecin", new Medecin());
        return "medecins/add";
    }

    // Enregistrer un nouveau médecin
    @PostMapping("/save")
    public String saveMedecin(@ModelAttribute("medecin") Medecin medecin) {
        medecinService.saveMedecin(medecin);
        return "redirect:/medecins-view";
    }

    // Formulaire de modification d'un médecin existant
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        Medecin medecin = medecinService.getMedecinById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID médecin invalide : " + id));
        model.addAttribute("medecin", medecin);
        return "medecins/edit";
    }

    // Enregistrer les modifications d'un médecin
    @PostMapping("/update/{id}")
    public String updateMedecin(@PathVariable Long id, @ModelAttribute("medecin") Medecin medecin) {
        medecinService.updateMedecin(id, medecin);
        return "redirect:/medecins-view";
    }

    // Recherche d'un médecin par ID
    @GetMapping("/search")
    public String searchMedecinById(@RequestParam("id") Long id, Model model) {
        Optional<Medecin> optionalMedecin = medecinService.getMedecinById(id);
        if (optionalMedecin.isPresent()) {
            model.addAttribute("medecins", List.of(optionalMedecin.get()));
        } else {
            model.addAttribute("medecins", List.of());
            model.addAttribute("error", "Aucun médecin trouvé avec l'ID : " + id);
        }
        return "medecins/list"; // réutilise la même vue que pour la liste
    }

    // Supprimer un médecin
    @GetMapping("/delete")
    public String deleteMedecin(@RequestParam("id") Long id) {
        medecinService.deleteMedecin(id);
        return "redirect:/medecins-view";
    }
}
