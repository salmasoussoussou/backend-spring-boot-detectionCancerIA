package com.MEDCIN.g04.controllers;
import org.springframework.security.access.prepost.PreAuthorize;

import com.MEDCIN.g04.models.RendezVous;
import com.MEDCIN.g04.dto.RendezVousDTO;
import com.MEDCIN.g04.services.RendezVousService;
import com.MEDCIN.g04.models.Patient;
import com.MEDCIN.g04.models.Secretaire;
import com.MEDCIN.g04.repositories.PatientRepository;
import com.MEDCIN.g04.repositories.SecretaireRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rendezvous")
@CrossOrigin(origins = "*")
public class RendezVousController {

    private final RendezVousService rendezVousService;
    private final PatientRepository patientRepository;
    private final SecretaireRepository secretaireRepository;

    @Autowired
    public RendezVousController(RendezVousService rendezVousService,
                                PatientRepository patientRepository,
                                SecretaireRepository secretaireRepository) {
        this.rendezVousService = rendezVousService;
        this.patientRepository = patientRepository;
        this.secretaireRepository = secretaireRepository;
    }

    // 🔹 Ajouter un nouveau rendez-vous
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public RendezVousDTO createRendezVous(@RequestBody RendezVousDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient non trouvé"));
        Secretaire secretaire = secretaireRepository.findById(dto.getSecretaireId())
                .orElseThrow(() -> new RuntimeException("Secrétaire non trouvé"));

        RendezVous rdv = new RendezVous(dto.getDate(), dto.getMotif(), patient, secretaire);
        return new RendezVousDTO(rendezVousService.saveRendezVous(rdv));
    }

    // 🔹 Récupérer tous les rendez-vous
    @PreAuthorize("hasAnyAuthority('MEDECIN','ADMIN')")
    @GetMapping
    public List<RendezVousDTO> getAll() {
        return rendezVousService.getAllRendezVous()
                .stream()
                .map(RendezVousDTO::new)
                .collect(Collectors.toList());
    }

    // 🔹 Récupérer un rendez-vous par ID
    @PreAuthorize("hasAnyAuthority('MEDECIN','ADMIN')")
    @GetMapping("/{id}")
    public RendezVousDTO getById(@PathVariable Long id) {
        RendezVous rdv = rendezVousService.getRendezVousById(id);
        if (rdv == null) {
            throw new RuntimeException("Rendez-vous non trouvé");
        }
        return new RendezVousDTO(rdv);
    }

    // 🔹 Modifier un rendez-vous
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public RendezVousDTO updateRendezVous(@PathVariable Long id, @RequestBody RendezVousDTO dto) {
        RendezVous rdvExist = rendezVousService.getRendezVousById(id);
        if (rdvExist == null) {
            throw new RuntimeException("Rendez-vous non trouvé");
        }

        // Mettre à jour les champs du rendez-vous existant avec les nouveaux
        rdvExist.setDate(dto.getDate());
        rdvExist.setMotif(dto.getMotif());

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient non trouvé"));
        Secretaire secretaire = secretaireRepository.findById(dto.getSecretaireId())
                .orElseThrow(() -> new RuntimeException("Secrétaire non trouvé"));

        rdvExist.setPatient(patient);
        rdvExist.setSecretaire(secretaire);

        RendezVous updatedRdv = rendezVousService.saveRendezVous(rdvExist);
        return new RendezVousDTO(updatedRdv);
    }

    // 🔹 Supprimer un rendez-vous
    @PreAuthorize("hasAnyAuthority('MEDECIN','ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        rendezVousService.deleteRendezVous(id);
    }
}
