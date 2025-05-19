package com.MEDCIN.g04.controller;

import com.MEDCIN.g04.models.*;
import com.MEDCIN.g04.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin-dashboard")
public class AdminDashboardController {

    private final PatientService patientService;
    private final MedecinService medecinService;
    private final RendezVousService rendezvousSerice;
    private final AnalyseAIService analyseaiService;
    private final SecretaireService secretaireService;
    private final EspaceTravailService espacetravailService;
    private final DiagnosticService diagnosticService;



    public AdminDashboardController(PatientService patientService,
                                    MedecinService medecinService,
                                    RendezVousService rendezvousSerice,
                                    AnalyseAIService analyseaiService,
                                    SecretaireService secretaireService,
                                    EspaceTravailService espacetravailService,
                                    DiagnosticService diagnosticService) {
        this.patientService = patientService;
        this.medecinService = medecinService;
        this.rendezvousSerice = rendezvousSerice;
        this.analyseaiService = analyseaiService;
        this.secretaireService = secretaireService;
        this.espacetravailService = espacetravailService;
        this.diagnosticService = diagnosticService;
    }

    @GetMapping
    public String showAdminDashboard(Model model) {
        List<Patient> patients5 = patientService.getLast5Patients();
        List<Patient> patients = patientService.getAllPatients();
        List<Medecin> medecins = medecinService.getAllMedecins();
        List <RendezVous> rendezvous = rendezvousSerice.getAllRendezVous();
        List <AnalyseAI> analyseai = analyseaiService.getAllAnalyses();
        List <Secretaire> secretaires = secretaireService.getAllSecretaires();
        List <EspaceTravail> espace = espacetravailService.getAllEspaces();
        List <Diagnostic> diagnostic = diagnosticService.getAllDiagnostics();




        model.addAttribute("patients5", patients5);
        model.addAttribute("patients", patients);
        model.addAttribute("medecins", medecins);
        model.addAttribute("rendezvousList", rendezvous);
        model.addAttribute("analyseai", analyseai);
        model.addAttribute("secretaires", secretaires);
        model.addAttribute("espace", espace);
        model.addAttribute("diagnostic", diagnostic);




        model.addAttribute("patientCount5", patients5.size());
        model.addAttribute("patientCount", patients.size());
       model.addAttribute("medecinCount", medecins.size());
        model.addAttribute("rendezVousCount", rendezvous.size());
        model.addAttribute("analyseCount", analyseai.size());
        model.addAttribute("secretairesCount", secretaires.size());
        model.addAttribute("espaceCount", espace.size());
        model.addAttribute("diagnosticCount", diagnostic.size());




        return "admin-dashboard";
    }
}