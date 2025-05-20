package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.models.Secretaire;
import com.MEDCIN.g04.models.EspaceTravail;
import com.MEDCIN.g04.services.SecretaireService;
import com.MEDCIN.g04.services.EspaceTravailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/secretaires-view")
public class SecretaireViewController {

    private final SecretaireService secretaireService;
    private final EspaceTravailService espaceTravailService;

    @Autowired
    public SecretaireViewController(SecretaireService secretaireService,
                                    EspaceTravailService espaceTravailService) {
        this.secretaireService = secretaireService;
        this.espaceTravailService = espaceTravailService;
    }

    // ✅ Liste des secrétaires
    @GetMapping
    public String listSecretaires(Model model) {
        List<Secretaire> secretaires = secretaireService.getAllSecretaires();
        model.addAttribute("secretaires", secretaires);
        return "secretaires/list"; // à créer dans templates/secretaires/list.html
    }

    // ✅ Formulaire d'ajout
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("secretaire", new Secretaire());
        model.addAttribute("espacesTravail", espaceTravailService.getAllEspaces());
        return "secretaires/add";
    }


    // ✅ Enregistrement d'une nouvelle secrétaire
    @PostMapping("/save")
    public String saveSecretaire(@ModelAttribute("secretaire") Secretaire secretaire) {
        // Ne touche pas à l'espace de travail
        secretaire.setEspaceTravail(null); // Optionnel si le champ n'est pas obligatoire

        secretaireService.saveSecretaire(secretaire);
        return "redirect:/secretaires-view";
    }



    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        Secretaire secretaire = secretaireService.getSecretaireById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID invalide : " + id));

        List<EspaceTravail> espacesTravail = espaceTravailService.getAllEspaces(); // Créez ce service si besoin

        model.addAttribute("secretaire", secretaire);
        model.addAttribute("espacesTravail", espacesTravail);
        return "secretaires/edit";
    }


    @PostMapping("/update/{id}")
    public String updateSecretaire(@PathVariable Long id, @ModelAttribute Secretaire secretaire) {
        // On récupère l'ancienne secrétaire depuis la base
        Secretaire existingSecretaire = secretaireService.getSecretaireById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID invalide : " + id));

        // On préserve l'espace de travail existant
        secretaire.setEspaceTravail(existingSecretaire.getEspaceTravail());

        // On continue avec la mise à jour
        secretaireService.updateSecretaire(id, secretaire);

        return "redirect:/secretaires-view";
    }




    // ✅ Recherche par ID
    @GetMapping("/search")
    public String searchSecretaireById(@RequestParam("id") Long id, Model model) {
        Optional<Secretaire> optionalSecretaire = secretaireService.getSecretaireById(id);
        if (optionalSecretaire.isPresent()) {
            model.addAttribute("secretaires", List.of(optionalSecretaire.get()));
        } else {
            model.addAttribute("secretaires", List.of());
            model.addAttribute("error", "Aucun secrétaire trouvé avec l'ID : " + id);
        }
        return "secretaires/list";
    }

    // ✅ Supprimer une secrétaire
    @GetMapping("/delete")
    public String deleteSecretaire(@RequestParam("id") Long id) {
        secretaireService.deleteSecretaire(id);
        return "redirect:/secretaires-view";
    }
}
