package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.dto.EspaceTravailDTO;
import com.MEDCIN.g04.models.EspaceTravail;
import com.MEDCIN.g04.services.EspaceTravailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/espaces")
@CrossOrigin(origins = "*")
public class EspaceTravailController {

    @Autowired
    private EspaceTravailService espaceTravailService;

    // Méthode de conversion vers DTO
    private EspaceTravailDTO convertToDTO(EspaceTravail espace) {
//        List<Long> medecinsIds = espace.getMedecins().stream()
//                .map(m -> m.getId())
//                .collect(Collectors.toList());
//
//        List<Long> patientsIds = espace.getPatients().stream()
//                .map(p -> p.getPatientID())
//                .collect(Collectors.toList());
//
//        List<Long> secretaireIds = espace.getSecretaires().stream()
//                .map(s -> s.getId())
//                .collect(Collectors.toList());

        return new EspaceTravailDTO(
                espace.getId(),
                espace.getNom(),
                espace.getAdresse()
//                medecinsIds,
//                patientsIds,
//                secretaireIds
        );
    }

    @GetMapping
    public List<EspaceTravailDTO> getAllEspaces() {
        return espaceTravailService.getAllEspaces()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspaceTravailDTO> getEspaceById(@PathVariable Long id) {
        return espaceTravailService.getEspaceById(id)
                .map(espace -> ResponseEntity.ok(convertToDTO(espace)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EspaceTravailDTO> createEspace(@RequestBody EspaceTravail espaceTravail) {
        EspaceTravail created = espaceTravailService.createEspace(espaceTravail);
        return ResponseEntity.ok(convertToDTO(created));
    }


    @PutMapping("/{id}")
    public ResponseEntity<EspaceTravail> updateEspace(@PathVariable Long id, @RequestBody EspaceTravail espaceTravail) {
        EspaceTravail updated = espaceTravailService.updateEspace(id, espaceTravail);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspace(@PathVariable Long id) {
        espaceTravailService.deleteEspace(id);
        return ResponseEntity.noContent().build();
    }
}
