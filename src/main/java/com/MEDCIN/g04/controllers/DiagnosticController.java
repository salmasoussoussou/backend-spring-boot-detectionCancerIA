package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.models.Diagnostic;
import com.MEDCIN.g04.services.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diagnostics")
@CrossOrigin(origins = "*") // Pour autoriser les requêtes depuis Postman ou le frontend
public class DiagnosticController {

    @Autowired
    private DiagnosticService diagnosticService;

    // Ajouter un diagnostic
    @PostMapping
    public Diagnostic createDiagnostic(@RequestBody Diagnostic diagnostic) {
        return diagnosticService.saveDiagnostic(diagnostic);
    }

    // Récupérer tous les diagnostics
    @GetMapping
    public List<Diagnostic> getAllDiagnostics() {
        return diagnosticService.getAllDiagnostics();
    }

    // Récupérer un diagnostic par ID
    @GetMapping("/{id}")
    public Optional<Diagnostic> getDiagnosticById(@PathVariable Long id) {
        return diagnosticService.getDiagnosticById(id);
    }

    // Mettre à jour un diagnostic
    @PutMapping("/{id}")
    public Diagnostic updateDiagnostic(@PathVariable Long id, @RequestBody Diagnostic diagnosticDetails) {
        return diagnosticService.updateDiagnostic(id, diagnosticDetails);
    }

    // Supprimer un diagnostic
    @DeleteMapping("/{id}")
    public void deleteDiagnostic(@PathVariable Long id) {
        diagnosticService.deleteDiagnostic(id);
    }

    // Compter le nombre total de diagnostics
    @GetMapping("/count")
    public long countDiagnostics() {
        return diagnosticService.countDiagnostic();
    }
}
