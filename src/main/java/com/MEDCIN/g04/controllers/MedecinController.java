package com.MEDCIN.g04.controllers;

import com.MEDCIN.g04.dto.MedecinDTO;
import com.MEDCIN.g04.models.Medecin;
import com.MEDCIN.g04.services.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medecins")
@CrossOrigin(origins = "*")
public class MedecinController {

    private final MedecinService medecinService;

    @Autowired
    public MedecinController(MedecinService medecinService) {
        this.medecinService = medecinService;
    }

    // Conversion de Medecin vers MedecinDTO
    private MedecinDTO convertToDTO(Medecin medecin) {
        return new MedecinDTO(
                medecin.getId(),
                medecin.getNom(),
                medecin.getPrenom(),
                medecin.getEmail(),
                medecin.getEspaceTravail() != null ? medecin.getEspaceTravail().getId() : null
        );
    }

    // 🔹 Ajouter un médecin
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MedecinDTO addMedecin(@RequestBody Medecin medecin) {
        Medecin saved = medecinService.saveMedecin(medecin);
        return convertToDTO(saved);
    }

    // 🔹 Afficher tous les médecins
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<MedecinDTO> getAllMedecins() {
        return medecinService.getAllMedecins().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 🔹 Afficher un médecin par ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MedecinDTO getMedecinById(@PathVariable Long id) {
        Medecin medecin = medecinService.getMedecinById(id)
                .orElseThrow(() -> new RuntimeException("Médecin non trouvé"));
        return convertToDTO(medecin);
    }

    // 🔹 Modifier un médecin
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MedecinDTO updateMedecin(@PathVariable Long id, @RequestBody Medecin medecin) {
        Medecin updated = medecinService.updateMedecin(id, medecin);
        return convertToDTO(updated);
    }

    // 🔹 Supprimer un médecin
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteMedecin(@PathVariable Long id) {
        medecinService.deleteMedecin(id);
    }

    // 🔹 Rechercher un médecin par email
    @GetMapping("/search")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MedecinDTO getMedecinByEmail(@RequestParam String email) {
        Medecin medecin = medecinService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Médecin non trouvé"));
        return convertToDTO(medecin);
    }
}