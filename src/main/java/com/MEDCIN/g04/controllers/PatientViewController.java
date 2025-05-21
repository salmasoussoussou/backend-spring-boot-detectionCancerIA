package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.models.Patient;
import com.MEDCIN.g04.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patients-view")
public class PatientViewController {

    private final PatientService patientService;

    @Autowired
    public PatientViewController(PatientService patientService) {
        this.patientService = patientService;
    }

    //  Liste des patients
    @GetMapping
    public String listPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patients/list";
    }

    // Formulaire d'ajout
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/add";
    }

    //  Enregistrement d'un nouveau patient
    @PostMapping("/save")
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patients-view";
    }

    //  Formulaire de modification
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        Patient patient = patientService.getPatientById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID patient invalide: " + id));
        model.addAttribute("patient", patient);
        return "patients/edit";
    }

    //  Enregistrer les modifications
    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute Patient patient) {
        patientService.updatePatient(id, patient);
        return "redirect:/patients-view";
    }

    @GetMapping("/search")
    public String searchPatientById(@RequestParam("id") Long id, Model model) {
        Optional<Patient> optionalPatient = patientService.getPatientById(id);
        if (optionalPatient.isPresent()) {
            model.addAttribute("patients", List.of(optionalPatient.get()));
        } else {
            model.addAttribute("patients", List.of());
            model.addAttribute("error", "Aucun patient trouvé avec l'ID : " + id);
        }
        return "patients/list"; // utilise la même vue que pour la liste
    }


    //  Supprimer un patient
    @GetMapping("/delete")
    public String deletePatient(@RequestParam("id") Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients-view";
    }

}
