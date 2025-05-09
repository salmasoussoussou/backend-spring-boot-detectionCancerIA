package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.dto.DiagnosticDTO;
import com.MEDCIN.g04.services.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnostics")
@CrossOrigin(origins = "*") // Tu peux limiter à un domaine spécifique en prod
public class DiagnosticController {

    @Autowired
    private DiagnosticService diagnosticService;

    // 🔹 Ajouter un diagnostic
    @PostMapping
    public ResponseEntity<DiagnosticDTO> createDiagnostic(@RequestBody DiagnosticDTO diagnosticDTO) {
        DiagnosticDTO created = diagnosticService.saveDiagnostic(diagnosticDTO);
        return ResponseEntity.ok(created);
    }

    // 🔹 Récupérer tous les diagnostics
    @GetMapping
    public ResponseEntity<List<DiagnosticDTO>> getAllDiagnostics() {
        return ResponseEntity.ok(diagnosticService.getAllDiagnostics());
    }

    // 🔹 Récupérer un diagnostic par ID
    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticDTO> getDiagnosticById(@PathVariable Long id) {
        return ResponseEntity.ok(diagnosticService.getDiagnosticById(id));
    }

    // 🔹 Supprimer un diagnostic
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiagnostic(@PathVariable Long id) {
        diagnosticService.deleteDiagnostic(id);
        return ResponseEntity.noContent().build();
    }

    // (Facultatif) 🔹 Mettre à jour un diagnostic existant
    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticDTO> updateDiagnostic(@PathVariable Long id, @RequestBody DiagnosticDTO diagnosticDTO) {
        diagnosticDTO.setId(id);
        DiagnosticDTO updated = diagnosticService.saveDiagnostic(diagnosticDTO);
        return ResponseEntity.ok(updated);
    }
}
